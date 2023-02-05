package com.web.myquiz.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionPOJO implements Serializable {

    private int question_id;
    private String category;
    private String content;
    private boolean status;
    private List<Option> options;
    private boolean is_shortanswer;

    public QuestionPOJO(Question question, String category, List<Option> options) {
        this.question_id = question.getQuestion_id();
        this.category = category;
        this.content = question.getContent();
        this.status = question.isStatus();
        this.options = options;
        this.is_shortanswer = options.size() == 0 ? true : false;
    }

}
