package com.web.myquiz.service;

import com.web.myquiz.domain.User;

import java.util.Optional;

public interface LoginService {

    Optional<User> validateUser(String username, String password);

}
