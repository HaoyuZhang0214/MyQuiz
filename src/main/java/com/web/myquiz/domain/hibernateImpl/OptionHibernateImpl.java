package com.web.myquiz.domain.hibernateImpl;

import com.web.myquiz.domain.Option;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Options")
public class OptionHibernateImpl extends Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private int option_id;

    @Column(name = "question_id")
    private int question_id;

    @Column(name = "content")
    private String content;

    @Column(name = "is_solution")
    private boolean is_solution;

}
