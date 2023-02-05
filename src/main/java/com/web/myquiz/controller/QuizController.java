package com.web.myquiz.controller;

import com.web.myquiz.domain.*;
import com.web.myquiz.service.*;
import com.web.myquiz.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {

    private QuizService quizService;
    private CategoryService categoryService;
    private QuestionService questionService;
    private OptionService optionService;
    private QuizRecordService quizRecordService;
    private QuestionRecordService questionRecordService;
    private UserService userService;

    @Autowired
    public QuizController(QuizServiceImpl quizServiceImpl,
                          CategoryServiceImpl categoryServiceImpl,
                          QuestionServiceImpl questionServiceImpl,
                          OptionServiceImpl optionServiceImpl,
                          QuizRecordServiceImpl quizRecordServiceImpl,
                          QuestionRecordServiceImpl questionRecordServiceImpl,
                          UserServiceImpl userServiceImpl) {
        this.quizService = quizServiceImpl;
        this.categoryService = categoryServiceImpl;
        this.questionService = questionServiceImpl;
        this.optionService = optionServiceImpl;
        this.quizRecordService = quizRecordServiceImpl;
        this.questionRecordService = questionRecordServiceImpl;
        this.userService = userServiceImpl;
    }

    @GetMapping("/quiz/{name}/{index}")
    public String getQuizPage(@PathVariable("name") String name,
                              @PathVariable("index") int index,
                              HttpServletRequest request,
                              Model model) {
        Optional<Category> category = categoryService.getCategoryByName(name);
        if(category.isPresent()) {
            Optional<Quiz> quiz = quizService.getQuizToTake(category.get().getCategory_id());
            if(quiz.isPresent()) {
                List<QuestionEntity> questionEntities = new ArrayList<QuestionEntity>();
                List<Question> questions = questionService.getQuestionsByQuiz(quiz.get().getQuiz_id());
                if(questions.size() > 0) {
                    for(Question q: questions) {
                        List<Option> options = optionService.getOptionsByQuestion(q.getQuestion_id());
                        QuestionEntity question = new QuestionEntity(q, options);
                        questionEntities.add(question);
                    }
                }
                // first question as default
                HttpSession session = request.getSession(false);
                session.setAttribute("questionNumber", index);
                session.setAttribute("questionEntities", questionEntities);
                session.setAttribute("quiz", quiz.get());
                session.setAttribute("name", category.get().getName());
                session.setAttribute("choice", new ArrayList<Integer>());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                session.setAttribute("start_time", timestamp.toString());
            }
        }
        return "quiz";
    }


    @PostMapping("/quiz/{name}/{index}")
    public String quizPageSwitch(@PathVariable("name") String name,
                                 @PathVariable("index") int index,
                                 @RequestParam(name="option", required=false) String option_id,
                                 HttpServletRequest request,
                                 Model model) {
        HttpSession session = request.getSession(false);
        if(option_id != null) {
            List<Integer> choice = (List<Integer>) session.getAttribute("choice");
            if(choice.size() > 0) {
                for(Integer op_id: choice) {
                    Optional<Option> op = optionService.getOptionById(op_id);
                    Optional<Option> op2 = optionService.getOptionById(Integer.parseInt(option_id));
                    if(op.isPresent() && op2.isPresent()) {
                        if(op.get().getQuestion_id()==op2.get().getQuestion_id()) {
                            choice.remove(op_id);
                            break;
                        }
                    }
                }
            }
            choice.add(Integer.parseInt(option_id));
            session.setAttribute("choice", choice);
        }
        session.setAttribute("questionNumber", index);
        return "quiz";
    }

    @PostMapping("/quiz/{name}")
    public String submitQuiz(@PathVariable("name") String name,
                             HttpServletRequest request,
                             Model model) {
        HttpSession session = request.getSession(false);
        List<Integer> choice = (List<Integer>) session.getAttribute("choice");
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        User user = (User) session.getAttribute("user");
        int score = 0;
        List<Option> options = new ArrayList<>();
        for(Integer op_id: choice) {
            Optional<Option> option = optionService.getOptionById(op_id);
            if(option.isPresent()) {
                options.add(option.get());
                if(option.get().is_solution()) {
                    score += 20;
                }
            }
        }
        quizRecordService.createQuizRecord(quiz.getQuiz_id(), user.getUser_id(), score);

        Optional<QuizRecord> quizRecord = quizRecordService.getQuizRecord(quiz.getQuiz_id(), user.getUser_id(), score);
        if(quizRecord.isPresent()) {
            int record_id = quizRecord.get().getRecord_id();
            for(Option op: options) {
                questionRecordService.createQuestionRecord(record_id, op.getQuestion_id(), op.getOption_id());
            }
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        session.setAttribute("end_time", timestamp.toString());
        model.addAttribute("score", score);
        return "quizResult";
    }


    @GetMapping("/quizResult/{record_id}")
    public String getQuizResultPage(@PathVariable Integer record_id,
                                    HttpServletRequest request,
                                    Model model) {
        HttpSession session = request.getSession(false);
        Optional<QuizRecord> quizRecord = quizRecordService.getQuizRecordById(record_id);
        if(quizRecord.isPresent()) {
            List<QuestionEntity> questionEntities = new ArrayList<QuestionEntity>();
            List<Question> questions = questionService.getQuestionsByQuiz(quizRecord.get().getQuiz_id());
            if(questions.size() > 0) {
                for(Question q: questions) {
                    List<Option> options = optionService.getOptionsByQuestion(q.getQuestion_id());
                    QuestionEntity question = new QuestionEntity(q, options);
                    questionEntities.add(question);
                }
            }
            Optional<Quiz> quiz = quizService.getQuizById(quizRecord.get().getQuiz_id());
            if(quiz.isPresent()) session.setAttribute("quiz", quiz.get());
            model.addAttribute("questionEntities", questionEntities);
            model.addAttribute("score", quizRecord.get().getScore());
        }
        List<QuestionRecord> questionRecords = questionRecordService.getQuestionRecordsByRecord(record_id);
        List<Integer> choice = new ArrayList<>();
        if(questionRecords.size()>0) {
            for(QuestionRecord qr: questionRecords) {
                choice.add(qr.getOption_id());
            }
            session.setAttribute("choice", choice);
        }
        return "quizResult";
    }


    @GetMapping("/quizResultDetail/{record_id}")
    public String getQuizResultDetailPage(@PathVariable Integer record_id,
                                    HttpServletRequest request,
                                    Model model) {
        HttpSession session = request.getSession(false);
        Optional<QuizRecord> quizRecord = quizRecordService.getQuizRecordById(record_id);
        if(quizRecord.isPresent()) {
            List<QuestionEntity> questionEntities = new ArrayList<QuestionEntity>();
            List<Question> questions = questionService.getQuestionsByQuiz(quizRecord.get().getQuiz_id());
            if(questions.size() > 0) {
                for(Question q: questions) {
                    List<Option> options = optionService.getOptionsByQuestion(q.getQuestion_id());
                    QuestionEntity question = new QuestionEntity(q, options);
                    questionEntities.add(question);
                }
            }
            model.addAttribute("date", quizRecord.get().getTaken_date());
            Optional<User> user = userService.getUserById(quizRecord.get().getUser_id());
            if(user.isPresent()) model.addAttribute("user", user.get());
            Optional<Quiz> quiz = quizService.getQuizById(quizRecord.get().getQuiz_id());
            if(quiz.isPresent()) session.setAttribute("quiz", quiz.get());
            model.addAttribute("questionEntities", questionEntities);
            model.addAttribute("score", quizRecord.get().getScore());
        }
        List<QuestionRecord> questionRecords = questionRecordService.getQuestionRecordsByRecord(record_id);
        List<Integer> choice = new ArrayList<>();
        if(questionRecords.size()>0) {
            for(QuestionRecord qr: questionRecords) {
                choice.add(qr.getOption_id());
                System.out.println(qr.getOption_id());
            }
            session.setAttribute("choice", choice);
        }
        return "quizResultDetail";
    }


    @GetMapping("/quizStart")
    public String getQuizStartPage(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "quizStart";
    }


}
