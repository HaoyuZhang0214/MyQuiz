package com.web.myquiz.service.impl;

import com.web.myquiz.domain.User;
import com.web.myquiz.service.LoginService;
import com.web.myquiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private UserService userService;

    @Autowired
    public LoginServiceImpl(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @Override
    public Optional<User> validateUser(String username, String password) {
        return userService.validateUser(username, password);
    }
}
