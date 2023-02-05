package com.web.myquiz.service;

import com.web.myquiz.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<Question> getQuestionsByQuiz(int quiz_id);

    List<Question> getAllQuestions();

    void updateQuestionStatus(int question_id);

    void addQuestion(int quiz_id, String content);

    Optional<Question> getQuestionById(int question_id);

    void updateQuestionContent(String content, int question_id);

}
