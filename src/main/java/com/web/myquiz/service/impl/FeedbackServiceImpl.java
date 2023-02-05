package com.web.myquiz.service.impl;

import com.web.myquiz.dao.FeedbackDao;
import com.web.myquiz.domain.Feedback;
import com.web.myquiz.service.FeedbackService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackDao feedbackDao;
    private boolean ormMode;

    public FeedbackServiceImpl(@Qualifier("feedbackDaoJdbcImpl") FeedbackDao feedbackDaoJdbcImpl,
                               @Qualifier("feedbackDaoHibernateImpl") FeedbackDao feedbackDaoHibernateImpl,
                               @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.feedbackDao = ormMode ? feedbackDaoHibernateImpl : feedbackDaoJdbcImpl;
    }

    @Override
    public void createFeedback(int user_id, int rating, String content) {
        feedbackDao.createFeedback(user_id, rating, content);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackDao.getAllFeedbacks();
    }


}
