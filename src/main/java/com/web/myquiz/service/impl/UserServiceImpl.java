package com.web.myquiz.service.impl;

import com.web.myquiz.dao.UserDao;
import com.web.myquiz.domain.User;
import com.web.myquiz.domain.jdbcImpl.UserJdbcImpl;
import com.web.myquiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private final boolean ormMode;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoJdbcImpl") UserDao userDaoJdbcImpl,
                           @Qualifier("userDaoHibernateImpl") UserDao userDaoHibernateImpl,
                           @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.userDao = ormMode ? userDaoHibernateImpl : userDaoJdbcImpl;
    }

    @Override
    public Optional<User> validateUser(String username, String password) {
        return userDao.getValidUser(username, password);
    }

    @Override
    public void createUser(String username, String password, String firstname, String lastname) {
        userDao.createUser(username, password, firstname, lastname);
    }

    @Override
    public Optional<User> getUserById(int user_id) {
        return userDao.getUserById(user_id);
    }

    @Override
    public void updateUserAccount(int user_id, String password, String firstname, String lastname, String address, String email, String phone) {
        userDao.updateUserAccount(user_id, password, firstname, lastname, address, email, phone);
    }

    @Override
    public List<User> getAllUsersExceptAdmin() {
        return userDao.getAllUsersExceptAdmin();
    }

    @Override
    public void updateUserStatus(int user_id) {
        userDao.updateUserStatus(user_id);
    }


}
