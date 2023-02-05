package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.QuizRecordDao;
import com.web.myquiz.domain.Category;
import com.web.myquiz.domain.QuizRecord;
import com.web.myquiz.domain.rowmapper.QuizRecordRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository("quizRecordDaoJdbcImpl")
public class QuizRecordDaoJdbcImpl implements QuizRecordDao {

    JdbcTemplate jdbcTemplate;
    QuizRecordRowMapper quizRecordRowMapper;

    @Autowired
    public QuizRecordDaoJdbcImpl(JdbcTemplate jdbcTemplate, QuizRecordRowMapper quizRecordRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.quizRecordRowMapper = quizRecordRowMapper;
    }

    @Override
    public List<QuizRecord> getQuizRecordsByUserId(int user_id) {
        String query = "SELECT * FROM QuizRecord WHERE user_id = ?";
        List<QuizRecord> quizRecords =  jdbcTemplate.query(query, quizRecordRowMapper, user_id);
        return quizRecords;
    }

    @Override
    public Optional<QuizRecord> getQuizRecord(int quiz_id, int user_id, int score) {
        String query = "SELECT * FROM QuizRecord WHERE quiz_id = ? and user_id = ? and score =?";
        List<QuizRecord> quizRecords =  jdbcTemplate.query(query, quizRecordRowMapper, quiz_id, user_id, score);
        return quizRecords.size()==0 ? Optional.empty(): Optional.ofNullable(quizRecords.get(0));
    }

    @Override
    public void createQuizRecord(int quiz_id, int user_id, int score) {
        String query = "INSERT INTO QuizRecord (quiz_id, user_id, taken_date, score) values (?, ?, ?, ?)";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(query, quiz_id, user_id, timestamp, score);
    }

    @Override
    public List<QuizRecord> getAllQuizRecords() {
        String query = "SELECT * FROM QuizRecord";
        List<QuizRecord> quizRecords =  jdbcTemplate.query(query, quizRecordRowMapper);
        return quizRecords;
    }

    @Override
    public Optional<QuizRecord> getQuizRecordById(int record_id) {
        String query = "SELECT * FROM QuizRecord WHERE record_id = ?";
        List<QuizRecord> quizRecords = jdbcTemplate.query(query, quizRecordRowMapper, record_id);
        return quizRecords.size()==0? Optional.empty(): Optional.ofNullable(quizRecords.get(0));
    }

}
