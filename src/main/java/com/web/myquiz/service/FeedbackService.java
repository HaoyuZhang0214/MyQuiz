package com.web.myquiz.service;

import com.web.myquiz.domain.Feedback;

import java.util.List;

public interface FeedbackService {

    void createFeedback(int user_id, int rating, String content);

    List<Feedback> getAllFeedbacks();

}
