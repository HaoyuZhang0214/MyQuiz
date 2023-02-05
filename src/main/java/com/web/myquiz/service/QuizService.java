package com.web.myquiz.service;

import com.web.myquiz.domain.Quiz;

import java.util.Optional;

public interface QuizService {

    Optional<Quiz> getQuizById(int quiz_id);

    Optional<Quiz> getQuizToTake(int category_id);

}
