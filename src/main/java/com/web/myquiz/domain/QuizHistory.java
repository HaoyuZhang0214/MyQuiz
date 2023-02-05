package com.web.myquiz.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuizHistory implements Serializable {
    private String name;
    private String taken_date;
    private String link;
}