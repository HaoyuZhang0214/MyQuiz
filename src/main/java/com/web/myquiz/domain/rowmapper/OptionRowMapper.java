package com.web.myquiz.domain.rowmapper;

import com.web.myquiz.domain.Option;
import com.web.myquiz.domain.jdbcImpl.OptionJdbcImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OptionRowMapper implements RowMapper<Option> {
    @Override
    public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
        OptionJdbcImpl option = new OptionJdbcImpl();
        option.setOption_id(rs.getInt("option_id"));
        option.setQuestion_id(rs.getInt("question_id"));
        option.setContent(rs.getString("content"));
        option.set_solution(rs.getBoolean("is_solution"));
        return option;
    }

}
