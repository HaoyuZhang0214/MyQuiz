package com.web.myquiz.dao;

import com.web.myquiz.domain.Feedback;

import java.util.List;

public interface FeedbackDao {

    void createFeedback(int user_id, int rating, String content);

    List<Feedback> getAllFeedbacks();

}
