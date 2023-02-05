package com.web.myquiz.domain;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuizResult implements Serializable {

    private String fullname;
    private String category;
    private int numOfQuestions;
    private int score;
    private Timestamp taken_date;
    private String link;

}
