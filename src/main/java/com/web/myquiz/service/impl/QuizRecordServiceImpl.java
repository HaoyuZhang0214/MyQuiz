package com.web.myquiz.service.impl;

import com.web.myquiz.dao.QuizRecordDao;
import com.web.myquiz.domain.QuizRecord;
import com.web.myquiz.service.QuizRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizRecordServiceImpl implements QuizRecordService {

    private QuizRecordDao quizRecordDao;
    private final boolean ormMode;

    @Autowired
    public QuizRecordServiceImpl(@Qualifier("quizRecordDaoJdbcImpl") QuizRecordDao quizRecordDaoJdbcImpl,
                                 @Qualifier("quizRecordDaoHibernateImpl") QuizRecordDao quizRecordDaoHibernateImpl,
                                 @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.quizRecordDao = ormMode ? quizRecordDaoHibernateImpl : quizRecordDaoJdbcImpl;
    }

    @Override
    public List<QuizRecord> getQuizRecordsByUserId(int user_id) {
        return quizRecordDao.getQuizRecordsByUserId(user_id);
    }

    @Override
    public Optional<QuizRecord> getQuizRecord(int quiz_id, int user_id, int score) {
        return quizRecordDao.getQuizRecord(quiz_id, user_id, score);
    }

    @Override
    public void createQuizRecord(int quiz_id, int user_id, int score) {
        quizRecordDao.createQuizRecord(quiz_id, user_id, score);
    }

    @Override
    public List<QuizRecord> getAllQuizRecords() {
        return quizRecordDao.getAllQuizRecords();
    }

    @Override
    public Optional<QuizRecord> getQuizRecordById(int record_id) {
        return quizRecordDao.getQuizRecordById(record_id);
    }


}
