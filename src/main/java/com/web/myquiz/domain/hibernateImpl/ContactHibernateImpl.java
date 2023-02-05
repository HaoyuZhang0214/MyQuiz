package com.web.myquiz.domain.hibernateImpl;

import com.web.myquiz.domain.Contact;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Contact")
public class ContactHibernateImpl extends Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contact_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

}
