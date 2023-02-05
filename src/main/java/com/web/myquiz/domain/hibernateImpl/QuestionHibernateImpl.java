package com.web.myquiz.domain.hibernateImpl;

import com.web.myquiz.domain.Question;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Question")
public class QuestionHibernateImpl extends Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int question_id;

    @Column(name = "quiz_id")
    private int quiz_id;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private boolean status;

}
