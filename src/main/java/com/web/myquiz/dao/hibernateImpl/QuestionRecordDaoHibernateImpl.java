package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.QuestionRecordDao;
import com.web.myquiz.domain.QuestionRecord;
import com.web.myquiz.domain.hibernateImpl.QuestionRecordHibernateImpl;
import com.web.myquiz.domain.hibernateImpl.QuizHibernateImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository("questionRecordDaoHibernateImpl")
public class QuestionRecordDaoHibernateImpl implements QuestionRecordDao {

    @Autowired
    private SessionFactory sessionFactory;

    public QuestionRecordDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createQuestionRecord(int record_id, int question_id, int option_id) {
        Session session = sessionFactory.getCurrentSession();
        QuestionRecordHibernateImpl questionRecord = QuestionRecordHibernateImpl.builder()
                .record_id(record_id)
                .question_id(question_id)
                .option_id(option_id)
                .build();
        session.save(questionRecord);
    }

    @Override
    public List<QuestionRecord> getQuestionRecordsByRecord(int record_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionRecordHibernateImpl> criteria = builder.createQuery(QuestionRecordHibernateImpl.class);
        Root<QuestionRecordHibernateImpl> root = criteria.from(QuestionRecordHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("record_id"), record_id));

        Query<QuestionRecordHibernateImpl> query = session.createQuery(criteria);
        return new ArrayList<>(query.getResultList());
    }
}
