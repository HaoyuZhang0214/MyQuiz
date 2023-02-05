package com.web.myquiz.domain.rowmapper;

import com.web.myquiz.domain.Question;
import com.web.myquiz.domain.jdbcImpl.QuestionJdbcImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuestionJdbcImpl question = new QuestionJdbcImpl();
        question.setQuestion_id(rs.getInt("question_id"));
        question.setQuiz_id(rs.getInt("quiz_id"));
        question.setContent(rs.getString("content"));
        question.setStatus(rs.getBoolean("status"));
        return question;
    }
}
