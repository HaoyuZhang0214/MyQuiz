package com.web.myquiz.service.impl;

import com.web.myquiz.dao.QuestionRecordDao;
import com.web.myquiz.domain.QuestionRecord;
import com.web.myquiz.service.QuestionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionRecordServiceImpl implements QuestionRecordService {

    private QuestionRecordDao questionRecordDao;
    private final boolean ormMode;

    @Autowired
    public QuestionRecordServiceImpl(@Qualifier("questionRecordDaoJdbcImpl") QuestionRecordDao questionRecordDaoJdbcImpl,
                                     @Qualifier("questionRecordDaoHibernateImpl") QuestionRecordDao questionRecordDaoHibernateImpl,
                                     @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.questionRecordDao = ormMode ? questionRecordDaoHibernateImpl: questionRecordDaoJdbcImpl;
    }

    @Override
    public void createQuestionRecord(int record_id, int question_id, int option_id) {
        questionRecordDao.createQuestionRecord(record_id, question_id, option_id);
    }

    @Override
    public List<QuestionRecord> getQuestionRecordsByRecord(int record_id) {
        return questionRecordDao.getQuestionRecordsByRecord(record_id);
    }


}
