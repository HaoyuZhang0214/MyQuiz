package com.web.myquiz.controller;

import com.web.myquiz.domain.User;
import com.web.myquiz.domain.Feedback;
import com.web.myquiz.service.FeedbackService;
import com.web.myquiz.service.impl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackServiceImpl feedbackServiceImpl) {
        this.feedbackService = feedbackServiceImpl;
    }

    @GetMapping("/feedback")
    public String getFeedbackPage() {
        return "/feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@RequestParam String rating,
                                 @RequestParam String content,
                                 HttpServletRequest request) {
        System.out.println(rating);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        feedbackService.createFeedback(user.getUser_id(), Integer.parseInt(rating), content);
        return "feedback";
    }



}
