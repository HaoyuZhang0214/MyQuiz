package com.web.myquiz.domain.hibernateImpl;

import com.web.myquiz.domain.Feedback;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Feedback")
public class FeedbackHibernateImpl extends Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedback_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private String date;

}
