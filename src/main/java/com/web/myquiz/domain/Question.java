package com.web.myquiz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class Question implements Serializable {

    private int question_id;
    private int quiz_id;
    private String content;
    private boolean status;

}
