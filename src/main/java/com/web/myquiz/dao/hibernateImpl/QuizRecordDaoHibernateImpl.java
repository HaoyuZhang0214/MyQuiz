package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.QuizRecordDao;
import com.web.myquiz.domain.QuizRecord;
import com.web.myquiz.domain.hibernateImpl.CategoryHibernateImpl;
import com.web.myquiz.domain.hibernateImpl.FeedbackHibernateImpl;
import com.web.myquiz.domain.hibernateImpl.QuizRecordHibernateImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository("quizRecordDaoHibernateImpl")
public class QuizRecordDaoHibernateImpl implements QuizRecordDao {

    @Autowired
    private SessionFactory sessionFactory;

    public QuizRecordDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<QuizRecord> getQuizRecordsByUserId(int user_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuizRecordHibernateImpl> criteria = builder.createQuery(QuizRecordHibernateImpl.class);
        Root<QuizRecordHibernateImpl> root = criteria.from(QuizRecordHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("user_id"), user_id));

        Query<QuizRecordHibernateImpl> query = session.createQuery(criteria);
        List<QuizRecordHibernateImpl> quizRecords = query.getResultList();
        return new ArrayList<>(quizRecords);
    }

    @Override
    public Optional<QuizRecord> getQuizRecord(int quiz_id, int user_id, int score) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuizRecordHibernateImpl> criteria = builder.createQuery(QuizRecordHibernateImpl.class);
        Root<QuizRecordHibernateImpl> root = criteria.from(QuizRecordHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("quiz_id"), quiz_id), builder.equal(root.get("user_id"), user_id), builder.equal(root.get("score"), score));

        Query<QuizRecordHibernateImpl> query = session.createQuery(criteria);
        List<QuizRecordHibernateImpl> quizRecords = query.getResultList();
        return quizRecords.size()==0? Optional.empty(): Optional.ofNullable(quizRecords.get(0));
    }

    @Override
    public void createQuizRecord(int quiz_id, int user_id, int score) {
        Session session = sessionFactory.getCurrentSession();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        QuizRecordHibernateImpl quizRecord = QuizRecordHibernateImpl.builder()
                .quiz_id(quiz_id)
                .user_id(user_id)
                .taken_date(timestamp.toString())
                .score(score)
                .build();
        session.save(quizRecord);
    }

    @Override
    public List<QuizRecord> getAllQuizRecords() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuizRecordHibernateImpl> criteria = builder.createQuery(QuizRecordHibernateImpl.class);
        Root<QuizRecordHibernateImpl> root = criteria.from(QuizRecordHibernateImpl.class);
        criteria.select(root);

        Query<QuizRecordHibernateImpl> query = session.createQuery(criteria);
        List<QuizRecordHibernateImpl> quizRecords = query.getResultList();
        return new ArrayList<>(quizRecords);
    }

    @Override
    public Optional<QuizRecord> getQuizRecordById(int record_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuizRecordHibernateImpl> criteria = builder.createQuery(QuizRecordHibernateImpl.class);
        Root<QuizRecordHibernateImpl> root = criteria.from(QuizRecordHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("record_id"), record_id));

        Query<QuizRecordHibernateImpl> query = session.createQuery(criteria);
        List<QuizRecordHibernateImpl> quizRecords = query.getResultList();
        return quizRecords.size()==0? Optional.empty(): Optional.ofNullable(quizRecords.get(0));
    }


}
