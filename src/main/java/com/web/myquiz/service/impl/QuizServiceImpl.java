package com.web.myquiz.service.impl;

import com.web.myquiz.dao.QuizDao;
import com.web.myquiz.domain.Quiz;
import com.web.myquiz.service.QuizService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizDao quizDao;
    private final boolean ormMode;

    public QuizServiceImpl(@Qualifier("quizDaoJdbcImpl") QuizDao quizDaoJdbcImpl,
                           @Qualifier("quizDaoHibernateImpl") QuizDao quizDaoHibernateImpl,
                           @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.quizDao = ormMode ? quizDaoHibernateImpl : quizDaoJdbcImpl;
    }

    @Override
    public Optional<Quiz> getQuizById(int quiz_id) {
        return quizDao.getQuizById(quiz_id);
    }

    @Override
    public Optional<Quiz> getQuizToTake(int category_id) {
        return quizDao.getQuizByCategoryRandomly(category_id);
    }


}
