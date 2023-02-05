package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.UserDao;
import com.web.myquiz.domain.User;
import com.web.myquiz.domain.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

    JdbcTemplate jdbcTemplate;
    UserRowMapper userRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDaoJdbcImpl(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<User> getValidUser(String username, String password) {
        String query = "SELECT * FROM User WHERE username = ? and password = ? and status = ?";
        List<User> users =  jdbcTemplate.query(query, userRowMapper, username, password, true);
        return users.size() == 0 ? Optional.empty() : Optional.ofNullable(users.get(0));
    }

    @Override
    public void createUser(String username, String password, String firstname, String lastname) {
        String query = "INSERT INTO User (username, password, firstname, lastname, status, is_admin) values (?, ?, ?, ?, ?, ?)";
        boolean status = true, is_admin = false;
        jdbcTemplate.update(query, username, password, firstname, lastname, status, is_admin);
    }

    @Override
    public Optional<User> getUserById(int user_id) {
        String query = "SELECT * FROM User WHERE user_id = ?";
        List<User> users = jdbcTemplate.query(query, userRowMapper, user_id);
        return users.size() == 0 ? Optional.empty() : Optional.ofNullable(users.get(0));
    }

    @Override
    public void updateUserAccount(int user_id, String password, String firstname, String lastname, String address, String email, String phone) {
        String query = "UPDATE User SET password = ?, firstname = ?, lastname = ?, address = ?, email = ?, phone = ? WHERE user_id = ?";
        jdbcTemplate.update(query, password, firstname, lastname, address, email, phone, user_id);
    }

    @Override
    public List<User> getAllUsersExceptAdmin() {
        String query = "SELECT * FROM User WHERE is_admin = ?";
        boolean is_admin = false;
        List<User> users = jdbcTemplate.query(query, userRowMapper, is_admin);
        return users;
    }

    @Override
    public void updateUserStatus(int user_id) {
        String query = "UPDATE User SET status = ? WHERE user_id = ?";
        Optional<User> user = getUserById(user_id);
        boolean status = true;
        if(user.isPresent()) {
            status = user.get().isStatus()? false: true;
        }
        jdbcTemplate.update(query, status, user_id);
    }


}
