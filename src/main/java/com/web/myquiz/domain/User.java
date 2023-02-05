package com.web.myquiz.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class User implements Serializable {

    private int user_id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String phone;
    private boolean status;
    private boolean is_admin;

}
