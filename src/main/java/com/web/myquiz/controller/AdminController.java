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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private FeedbackService feedbackService;
    private UserService userService;
    private QuestionService questionService;
    private OptionService optionService;
    private CategoryService categoryService;
    private QuizService quizService;

    @Autowired
    public AdminController(FeedbackServiceImpl feedbackServiceImpl,
                           UserServiceImpl userServiceImpl,
                           QuestionServiceImpl questionServiceImpl,
                           OptionServiceImpl optionServiceImpl,
                           CategoryServiceImpl categoryServiceImpl,
                           QuizServiceImpl quizServiceImpl) {
        this.feedbackService = feedbackServiceImpl;
        this.userService = userServiceImpl;
        this.questionService = questionServiceImpl;
        this.optionService = optionServiceImpl;
        this.categoryService = categoryServiceImpl;
        this.quizService = quizServiceImpl;
    }

    @GetMapping("/feedbackAdmin")
    public String gerFeedbackAdminPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        session.setAttribute("feedbacks", feedbacks);
        return "feedbackAdmin";
    }


    @GetMapping("/user/manage")
    public String getUserManagePage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<User> users = userService.getAllUsersExceptAdmin();
        session.setAttribute("users", users);
        return "userManage";
    }


    @PostMapping("/user/manage/{user_id}")
    public String userManage(@PathVariable int user_id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        userService.updateUserStatus(user_id);
        List<User> users = userService.getAllUsersExceptAdmin();
        session.setAttribute("users", users);
        return "userManage";
    }

    @GetMapping("/question/manage")
    public String getQuestionManagePage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<Question> questions = questionService.getAllQuestions();
        List<QuestionPOJO> questionPOJOs = new ArrayList<>();
        if(questions.size()>0) {
            for(Question q: questions) {
                Optional<Quiz> quiz = quizService.getQuizById(q.getQuiz_id());
                if(quiz.isPresent()) {
                    Optional<Category> category = categoryService.getCategoryById(quiz.get().getCategory_id());
                    if(category.isPresent()) {
                        QuestionPOJO qPOJO = new QuestionPOJO(q, category.get().getName(), optionService.getOptionsByQuestion(q.getQuestion_id()));
                        questionPOJOs.add(qPOJO);
                    }
                }
            }
        }
        session.setAttribute("questionPOJOs", questionPOJOs);
        return "questionManage";
    }


    @PostMapping("/question/manage/{question_id}")
    public String questionManage(@PathVariable int question_id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        questionService.updateQuestionStatus(question_id);

        List<Question> questions = questionService.getAllQuestions();
        List<QuestionPOJO> questionPOJOs = new ArrayList<>();
        if(questions.size()>0) {
            for(Question q: questions) {
                Optional<Quiz> quiz = quizService.getQuizById(q.getQuiz_id());
                if(quiz.isPresent()) {
                    Optional<Category> category = categoryService.getCategoryById(quiz.get().getCategory_id());
                    if(category.isPresent()) {
                        QuestionPOJO qPOJO = new QuestionPOJO(q, category.get().getName(), optionService.getOptionsByQuestion(q.getQuestion_id()));
                        questionPOJOs.add(qPOJO);
                    }
                }
            }
        }
        session.setAttribute("questionPOJOs", questionPOJOs);
        return "questionManage";
    }


    @GetMapping("/question/manage/add")
    public String getAddQuestionPage() {
        return "addQuestion";
    }


    @PostMapping("/question/manage/add")
    public String addNewQuestion(@RequestParam String content,
                                 @RequestParam String option1,
                                 @RequestParam String option2,
                                 @RequestParam String option3,
                                 @RequestParam String option4,
                                 @RequestParam int solution,
                                 HttpServletRequest request) {
        Integer category_id = Integer.valueOf(request.getParameter("category"));
        Optional<Quiz> quiz = quizService.getQuizToTake(category_id);
        List<String> contents = new ArrayList<>();
        contents.add(option1);
        contents.add(option2);
        contents.add(option3);
        contents.add(option4);
        if(quiz.isPresent()) {
            questionService.addQuestion(quiz.get().getQuiz_id(), content);
            List<Question> questions = questionService.getQuestionsByQuiz(quiz.get().getQuiz_id());
            int question_id = questions.get(questions.size()-1).getQuestion_id();
            optionService.createOptions(question_id, contents, solution);
        }
        return "questionManage";
    }


    @PostMapping("/question/update/{question_id}")
    public String getUpdateQuestionPage(@PathVariable Integer question_id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Optional<Question> question = questionService.getQuestionById(question_id);
        List<Option> options = optionService.getOptionsByQuestion(question_id);
        if(question.isPresent()) session.setAttribute("question", question.get());
        model.addAttribute("options", options);
        return "updateQuestion";
    }


    @PostMapping("/question/manage/update")
    public String updateQuestion(@RequestParam String content,
                                 @RequestParam String option1,
                                 @RequestParam String option2,
                                 @RequestParam String option3,
                                 @RequestParam String option4,
                                 @RequestParam int solution,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Question question = (Question) session.getAttribute("question");
        questionService.updateQuestionContent(content, question.getQuestion_id());
        List<Option> options = optionService.getOptionsByQuestion(question.getQuestion_id());
        if(options.size()>=4) {
            optionService.updateOption(options.get(0).getOption_id(), option1, (solution==1)? true: false);
            optionService.updateOption(options.get(1).getOption_id(), option2, (solution==2)? true: false);
            optionService.updateOption(options.get(2).getOption_id(), option3, (solution==3)? true: false);
            optionService.updateOption(options.get(3).getOption_id(), option4, (solution==4)? true: false);
        }
        return "questionManage";
    }

}
