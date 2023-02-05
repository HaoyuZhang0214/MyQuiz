package com.web.myquiz.dao;

import com.web.myquiz.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> getValidUser(String username, String password);
    void createUser(String username, String password, String firstname, String lastname);
    Optional<User> getUserById(int user_id);
    void updateUserAccount(int user_id,
                           String password,
                           String firstname,
                           String lastname,
                           String address,
                           String email,
                           String phone);

    List<User> getAllUsersExceptAdmin();

    void updateUserStatus(int user_id);

}
