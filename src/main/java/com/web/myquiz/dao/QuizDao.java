package com.web.myquiz.dao;

import com.web.myquiz.domain.Quiz;

import java.util.Optional;

public interface QuizDao {

    Optional<Quiz> getQuizById(int quiz_id);

    Optional<Quiz> getQuizByCategoryRandomly(int category_id);

}
