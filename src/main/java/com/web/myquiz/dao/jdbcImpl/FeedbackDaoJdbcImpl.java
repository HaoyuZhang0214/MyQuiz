package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.FeedbackDao;
import com.web.myquiz.domain.Feedback;
import com.web.myquiz.domain.rowmapper.FeedbackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("feedbackDaoJdbcImpl")
public class FeedbackDaoJdbcImpl implements FeedbackDao {

    JdbcTemplate jdbcTemplate;
    FeedbackRowMapper feedbackRowMapper;

    @Autowired
    public FeedbackDaoJdbcImpl(JdbcTemplate jdbcTemplate, FeedbackRowMapper feedbackRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.feedbackRowMapper = feedbackRowMapper;
    }

    @Override
    public void createFeedback(int user_id, int rating, String content) {
        String query = "INSERT INTO Feedback (user_id, rating, content, date) values (?, ?, ?, ?)";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(query, user_id, rating, content, timestamp);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        String query = "SELECT * FROM Feedback";
        List<Feedback> feedbacks = jdbcTemplate.query(query, feedbackRowMapper);
        return feedbacks;
    }
}
