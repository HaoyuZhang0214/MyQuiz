package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.OptionDao;
import com.web.myquiz.domain.Option;
import com.web.myquiz.domain.User;
import com.web.myquiz.domain.rowmapper.OptionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("optionDaoJdbcImpl")
public class OptionDaoJdbcImpl implements OptionDao {

    JdbcTemplate jdbcTemplate;
    OptionRowMapper optionRowMapper;

    @Autowired
    public OptionDaoJdbcImpl(JdbcTemplate jdbcTemplate, OptionRowMapper optionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.optionRowMapper = optionRowMapper;
    }


    @Override
    public List<Option> getOptionsByQuestion(int question_id) {
        String query = "SELECT * FROM Options WHERE question_id = ?";
        List<Option> options = jdbcTemplate.query(query, optionRowMapper, question_id);
        return options;
    }

    @Override
    public Optional<Option> getOptionById(int option_id) {
        String query = "SELECT * FROM Options WHERE option_id = ?";
        List<Option> options = jdbcTemplate.query(query, optionRowMapper, option_id);
        return options.size() == 0 ? Optional.empty() : Optional.ofNullable(options.get(0));
    }

    @Override
    public void createOptions(int question_id, List<String> contents, int solution) {
        String query = "INSERT INTO Options (question_id, content, is_solution) VALUES (?, ?, ?)";
        for(int i = 0; i < contents.size(); i++) {
            jdbcTemplate.update(query,question_id, contents.get(i), (i==solution-1)?true: false);
        }
    }

    @Override
    public void updateOption(int option_id, String content, boolean is_solution) {
        String query = "UPDATE Options SET content = ?, is_solution = ? WHERE option_id = ?";
        jdbcTemplate.update(query, content, is_solution, option_id);
    }
}
