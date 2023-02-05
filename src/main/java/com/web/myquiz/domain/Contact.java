package com.web.myquiz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class Contact implements Serializable {

    private int contact_id;
    private int user_id;
    private String subject;
    private String message;

}
