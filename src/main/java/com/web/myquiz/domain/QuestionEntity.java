package com.web.myquiz.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionEntity implements Serializable {

    private int question_id;
    private int quiz_id;
    private String content;
    private boolean status;
    private List<Option> options;
    private boolean is_shortanswer;

    public QuestionEntity(Question question, List<Option> options) {
        this.question_id = question.getQuestion_id();
        this.quiz_id = question.getQuiz_id();
        this.content = question.getContent();
        this.status = question.isStatus();
        this.options = options;
        this.is_shortanswer = options.size() == 0 ? true : false;
    }


}
