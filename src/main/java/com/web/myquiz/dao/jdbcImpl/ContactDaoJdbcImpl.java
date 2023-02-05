package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.ContactDao;
import com.web.myquiz.domain.rowmapper.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("contactDaoJdbcImpl")
public class ContactDaoJdbcImpl implements ContactDao {

    JdbcTemplate jdbcTemplate;
    ContactRowMapper contactRowMapper;

    @Autowired
    public ContactDaoJdbcImpl(JdbcTemplate jdbcTemplate, ContactRowMapper contactRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.contactRowMapper = contactRowMapper;
    }

    @Override
    public void createContact(int user_id, String subject, String message) {
        String query = "INSERT INTO Contact (user_id, subject, message) values (?, ?, ?)";
        jdbcTemplate.update(query, user_id, subject, message);
    }

}
