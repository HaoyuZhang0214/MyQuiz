package com.web.myquiz.domain.rowmapper;

import com.web.myquiz.domain.QuizRecord;
import com.web.myquiz.domain.jdbcImpl.QuizRecordJdbcImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRecordRowMapper implements RowMapper<QuizRecord> {

    @Override
    public QuizRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizRecordJdbcImpl quizRecord = new QuizRecordJdbcImpl();
        quizRecord.setRecord_id(rs.getInt("record_id"));
        quizRecord.setQuiz_id(rs.getInt("quiz_id"));
        quizRecord.setUser_id(rs.getInt("user_id"));
        quizRecord.setTaken_date(rs.getString("taken_date"));
        quizRecord.setScore(rs.getInt("score"));
        return quizRecord;
    }
}
