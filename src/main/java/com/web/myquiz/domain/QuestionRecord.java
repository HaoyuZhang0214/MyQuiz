package com.web.myquiz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class QuestionRecord implements Serializable {

    private int qr_id;
    private int record_id;
    private int question_id;
    private int option_id;

}
