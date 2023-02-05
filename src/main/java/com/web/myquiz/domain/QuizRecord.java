package com.web.myquiz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class QuizRecord implements Serializable {

    private int record_id;
    private int quiz_id;
    private int user_id;
    private String taken_date;
    private int score;
}
