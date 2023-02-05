package com.web.myquiz.domain.rowmapper;

import com.web.myquiz.domain.Quiz;
import com.web.myquiz.domain.jdbcImpl.QuizJdbcImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizJdbcImpl quiz = new QuizJdbcImpl();
        quiz.setQuiz_id(rs.getInt("quiz_id"));
        quiz.setCategory_id(rs.getInt("category_id"));
        quiz.setName(rs.getString("name"));
        return quiz;
    }
}
