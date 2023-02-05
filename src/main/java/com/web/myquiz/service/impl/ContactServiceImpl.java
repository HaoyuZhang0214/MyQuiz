package com.web.myquiz.service.impl;

import com.web.myquiz.dao.ContactDao;
import com.web.myquiz.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;
    private boolean ormMode;

    @Autowired
    public ContactServiceImpl(@Qualifier("contactDaoJdbcImpl") ContactDao contactDaoJdbcImpl,
                              @Qualifier("contactDaoHibernateImpl") ContactDao contactDaoHibernateImpl,
                              @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.contactDao = ormMode ? contactDaoHibernateImpl : contactDaoJdbcImpl;
    }

    @Override
    public void createContact(int user_id, String subject, String message) {
        contactDao.createContact(user_id, subject, message);
    }
}
