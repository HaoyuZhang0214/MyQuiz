package com.web.myquiz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class Option implements Serializable {

    private int option_id;
    private int question_id;
    private String content;
    private boolean is_solution;

}
