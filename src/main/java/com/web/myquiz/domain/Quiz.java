package com.web.myquiz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class Quiz implements Serializable {

    private int quiz_id;
    private int category_id;
    private String name;

}
