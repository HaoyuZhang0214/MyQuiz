package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.QuizDao;
import com.web.myquiz.domain.Quiz;
import com.web.myquiz.domain.User;
import com.web.myquiz.domain.rowmapper.QuizRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("quizDaoJdbcImpl")
public class QuizDaoJdbcImpl implements QuizDao {

    JdbcTemplate jdbcTemplate;
    QuizRowMapper quizRowMapper;

    public QuizDaoJdbcImpl(JdbcTemplate jdbcTemplate, QuizRowMapper quizRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.quizRowMapper = quizRowMapper;
    }

    @Override
    public Optional<Quiz> getQuizById(int quiz_id) {
        String query = "SELECT * FROM Quiz WHERE quiz_id = ?";
        List<Quiz> quizzes = jdbcTemplate.query(query, quizRowMapper, quiz_id);
        return quizzes.size() == 0 ? Optional.empty() : Optional.ofNullable(quizzes.get(0));
    }

    @Override
    public Optional<Quiz> getQuizByCategoryRandomly(int category_id) {
        String query = "SELECT * FROM Quiz WHERE category_id = ? ORDER BY RAND() LIMIT 1";
        List<Quiz> quizzes = jdbcTemplate.query(query, quizRowMapper, category_id);
        return quizzes.size() == 0 ? Optional.empty() : Optional.ofNullable(quizzes.get(0));
    }
}
