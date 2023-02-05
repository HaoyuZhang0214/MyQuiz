package com.web.myquiz.domain.rowmapper;

import com.web.myquiz.domain.QuestionRecord;
import com.web.myquiz.domain.jdbcImpl.QuestionRecordJdbcImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRecordRowMapper implements RowMapper<QuestionRecord> {
    @Override
    public QuestionRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuestionRecordJdbcImpl questionRecord = new QuestionRecordJdbcImpl();
        questionRecord.setQr_id(rs.getInt("qr_id"));
        questionRecord.setRecord_id(rs.getInt("record_id"));
        questionRecord.setQuestion_id(rs.getInt("question_id"));
        questionRecord.setOption_id(rs.getInt("option_id"));
        return questionRecord;
    }
}
