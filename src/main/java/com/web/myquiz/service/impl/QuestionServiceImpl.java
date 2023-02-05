package com.web.myquiz.service.impl;

import com.web.myquiz.dao.QuestionDao;
import com.web.myquiz.domain.Question;
import com.web.myquiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;
    private final boolean ormMode;

    @Autowired
    public QuestionServiceImpl(@Qualifier("questionDaoJdbcImpl") QuestionDao questionDaoJdbcImpl,
                               @Qualifier("questionDaoHibernateImpl") QuestionDao questionDaoHibernateImpl,
                               @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.questionDao = ormMode ? questionDaoHibernateImpl : questionDaoJdbcImpl;
    }

    @Override
    public List<Question> getQuestionsByQuiz(int quiz_id) {
        return questionDao.getQuestionsByQuiz(quiz_id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    @Override
    public void updateQuestionStatus(int question_id) {
        questionDao.updateQuestionStatus(question_id);
    }

    @Override
    public void addQuestion(int quiz_id, String content) {
        questionDao.addQuestion(quiz_id, content);
    }

    @Override
    public Optional<Question> getQuestionById(int question_id) {
        return questionDao.getQuestionById(question_id);
    }

    @Override
    public void updateQuestionContent(String content, int question_id) {
        questionDao.updateQuestionContent(content, question_id);
    }


}
