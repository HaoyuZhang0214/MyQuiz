package com.web.myquiz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class Category implements Serializable {

    private int category_id;
    private String name;

}
