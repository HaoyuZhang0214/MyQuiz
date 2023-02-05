package com.web.myquiz.domain.hibernateImpl;

import com.web.myquiz.domain.QuestionRecord;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "QuestionRecord")
public class QuestionRecordHibernateImpl extends QuestionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qr_id")
    private int qr_id;

    @Column(name = "record_id")
    private int record_id;

    @Column(name = "question_id")
    private int question_id;

    @Column(name = "option_id")
    private int option_id;

}
