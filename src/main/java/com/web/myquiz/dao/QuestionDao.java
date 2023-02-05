package com.web.myquiz.dao;

import com.web.myquiz.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {

    List<Question> getQuestionsByQuiz(int quiz_id);

    List<Question> getAllQuestions();

    void updateQuestionStatus(int question_id);

    Optional<Question> getQuestionById(int question_id);

    void addQuestion(int quiz_id, String content);

    void updateQuestionContent(String content, int question_id);

}
