package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.FeedbackDao;
import com.web.myquiz.domain.Feedback;
import com.web.myquiz.domain.hibernateImpl.FeedbackHibernateImpl;
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

@Transactional
@Repository("feedbackDaoHibernateImpl")
public class FeedbackDaoHibernateImpl implements FeedbackDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createFeedback(int user_id, int rating, String content) {
        Session session = sessionFactory.getCurrentSession();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FeedbackHibernateImpl feedback = FeedbackHibernateImpl.builder()
                .user_id(user_id)
                .rating(rating)
                .content(content)
                .date(timestamp.toString())
                .build();
        session.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FeedbackHibernateImpl> criteria = builder.createQuery(FeedbackHibernateImpl.class);
        Root<FeedbackHibernateImpl> root = criteria.from(FeedbackHibernateImpl.class);
        criteria.select(root);

        Query<FeedbackHibernateImpl> query = session.createQuery(criteria);
        List<FeedbackHibernateImpl> feedbacks = query.getResultList();
        return new ArrayList<>(feedbacks);
    }
}
