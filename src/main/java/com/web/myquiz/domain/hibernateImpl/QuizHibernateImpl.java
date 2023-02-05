package com.web.myquiz.domain.hibernateImpl;

import com.web.myquiz.domain.Quiz;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Quiz")
public class QuizHibernateImpl extends Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private int quiz_id;

    @Column(name = "category_id")
    private int category_id;

    @Column(name = "name")
    private String name;

}
