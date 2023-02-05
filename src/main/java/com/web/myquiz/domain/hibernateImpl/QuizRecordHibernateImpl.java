package com.web.myquiz.domain.hibernateImpl;

import com.web.myquiz.domain.QuizRecord;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "QuizRecord")
public class QuizRecordHibernateImpl extends QuizRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int record_id;

    @Column(name = "quiz_id")
    private int quiz_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "taken_date")
    private String taken_date;

    @Column(name = "score")
    private int score;

}
