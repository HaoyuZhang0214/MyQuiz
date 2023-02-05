package com.web.myquiz.domain.rowmapper;

import com.web.myquiz.domain.Feedback;
import com.web.myquiz.domain.jdbcImpl.FeedbackJdbcImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackRowMapper implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        FeedbackJdbcImpl feedback = new FeedbackJdbcImpl();
        feedback.setFeedback_id(rs.getInt("feedback_id"));
        feedback.setUser_id(rs.getInt("user_id"));
        feedback.setRating(rs.getInt("rating"));
        feedback.setContent(rs.getString("content"));
        feedback.setDate(rs.getString("date"));
        return feedback;
    }
}
