package com.web.myquiz.controller;

import com.web.myquiz.domain.*;
import com.web.myquiz.service.CategoryService;
import com.web.myquiz.service.QuizRecordService;
import com.web.myquiz.service.QuizService;
import com.web.myquiz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class HomeController {

    private static final int NUM_QUESTIONS = 5;
    private CategoryService categoryService;
    private QuizRecordService quizRecordService;
    private QuizService quizService;
    private UserService userService;

    public HomeController(CategoryService categoryServiceImpl,
                          QuizRecordService quizRecordServiceImpl,
                          QuizService quizServiceImpl,
                          UserService userServiceImpl) {
        this.categoryService = categoryServiceImpl;
        this.quizRecordService = quizRecordServiceImpl;
        this.quizService = quizServiceImpl;
        this.userService = userServiceImpl;
    }

    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<QuizRecord> quizRecords = quizRecordService.getQuizRecordsByUserId(user.getUser_id());

        List<QuizHistory> quizHistories = new ArrayList<QuizHistory>();
        if(quizRecords.size() > 0) {
            for(QuizRecord qr: quizRecords) {
                Optional<Quiz> quizop = quizService.getQuizById(qr.getQuiz_id());
                if(quizop.isPresent()) {
                    Quiz quiz = quizop.get();
                    quizHistories.add(new QuizHistory(quiz.getName(), qr.getTaken_date(), "/quizResult/"+qr.getRecord_id()));
                }
            }
        } else
            return "home";

        model.addAttribute("quizHistories", quizHistories);
        return "home";
    }


    @GetMapping("/homeAdmin")
    public String getHomeAdminPage(@RequestParam(name="category", required = false) String cat_name,
                                   @RequestParam(name="user", required = false) Integer u_id,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User admin = (User) session.getAttribute("user");

        List<Category> categories = categoryService.getAllCategories();
        session.setAttribute("categories", categories);

        List<User> users = userService.getAllUsersExceptAdmin();
        session.setAttribute("users",users);

        List<QuizResult> quizResults = new ArrayList<>();
        List<QuizRecord> quizRecords = quizRecordService.getAllQuizRecords();
        for(QuizRecord qr: quizRecords) {
            String fullname = null;
            Optional<User> user = userService.getUserById(qr.getUser_id());
            if(user.isPresent()) {
                if((u_id!=null && u_id!=0) && (user.get().getUser_id()!=u_id)) {
                    continue;
                }
                fullname = user.get().getFirstname() + " " + user.get().getLastname();
            }

            String category = null;
            Optional<Quiz> quiz = quizService.getQuizById(qr.getQuiz_id());
            if(quiz.isPresent()) {
                Optional<Category> category_op = categoryService.getCategoryById(quiz.get().getCategory_id());
                if(category_op.isPresent()) {
                    category = category_op.get().getName();
                    if((cat_name!=null) && (!cat_name.equals("All Categories")) && (!category.equals(cat_name)))
                        continue;
                }
            }
            Timestamp ts = Timestamp.valueOf(qr.getTaken_date());
            QuizResult quizResult = new QuizResult(fullname, category, NUM_QUESTIONS, qr.getScore(), ts, "/quizResultDetail/"+qr.getRecord_id());
            quizResults.add(quizResult);
        }
        Collections.sort(quizResults, Comparator.comparing(QuizResult::getTaken_date));
        Collections.reverse(quizResults);
        session.setAttribute("quizResults", quizResults);
        return "homeAdmin";
    }


//    @GetMapping("/homeAdmin/{category}")
//    public String getHomeAdminWithCategory(@PathVariable String category, HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//
//        return "homeAdmin";
//    }
}
