package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.QuestionRecordDao;
import com.web.myquiz.domain.QuestionRecord;
import com.web.myquiz.domain.rowmapper.QuestionRecordRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("questionRecordDaoJdbcImpl")
public class QuestionRecordDaoJdbcImpl implements QuestionRecordDao {

    JdbcTemplate jdbcTemplate;
    QuestionRecordRowMapper questionRecordRowMapper;

    @Autowired
    public QuestionRecordDaoJdbcImpl(JdbcTemplate jdbcTemplate, QuestionRecordRowMapper questionRecordRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.questionRecordRowMapper = questionRecordRowMapper;
    }


    @Override
    public void createQuestionRecord(int record_id, int question_id, int option_id) {
        String query = "INSERT INTO QuestionRecord (record_id, question_id, option_id) values (?, ?, ?)";
        jdbcTemplate.update(query, record_id, question_id, option_id);
    }

    @Override
    public List<QuestionRecord> getQuestionRecordsByRecord(int record_id) {
        String query = "SELECT * FROM QuestionRecord WHERE record_id = ?";
        return jdbcTemplate.query(query, questionRecordRowMapper, record_id);
    }
}
