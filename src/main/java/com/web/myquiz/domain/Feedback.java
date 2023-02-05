package com.web.myquiz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class Feedback implements Serializable {

    private int feedback_id;
    private int user_id;
    private int rating;
    private String content;
    private String date;

}
