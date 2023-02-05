package com.web.myquiz.domain.rowmapper;

import com.web.myquiz.domain.Contact;
import com.web.myquiz.domain.jdbcImpl.ContactJdbcImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContactJdbcImpl contact = new ContactJdbcImpl();
        contact.setContact_id(rs.getInt("contact_id"));
        contact.setUser_id(rs.getInt("user_id"));
        contact.setSubject(rs.getString("subject"));
        contact.setMessage(rs.getString("message"));
        return contact;
    }
}
