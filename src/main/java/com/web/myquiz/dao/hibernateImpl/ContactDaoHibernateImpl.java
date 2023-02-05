package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.ContactDao;
import com.web.myquiz.domain.hibernateImpl.ContactHibernateImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("contactDaoHibernateImpl")
public class ContactDaoHibernateImpl implements ContactDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createContact(int user_id, String subject, String message) {
        Session session = sessionFactory.getCurrentSession();
        ContactHibernateImpl contact = ContactHibernateImpl.builder()
                .user_id(user_id)
                .subject(subject)
                .message(message)
                .build();
        session.save(contact);
    }
}
