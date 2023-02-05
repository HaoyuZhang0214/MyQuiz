package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.QuizDao;
import com.web.myquiz.domain.Quiz;
import com.web.myquiz.domain.hibernateImpl.QuizHibernateImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository("quizDaoHibernateImpl")
public class QuizDaoHibernateImpl implements QuizDao {

    @Autowired
    private SessionFactory sessionFactory;

    public QuizDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Quiz> getQuizById(int quiz_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuizHibernateImpl> criteria = builder.createQuery(QuizHibernateImpl.class);
        Root<QuizHibernateImpl> root = criteria.from(QuizHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("quiz_id"), quiz_id));

        Query<QuizHibernateImpl> query = session.createQuery(criteria);
        List<QuizHibernateImpl> quizzes = query.getResultList();
        return quizzes.size() == 0 ? Optional.empty() : Optional.ofNullable(quizzes.get(0));
    }

    @Override
    public Optional<Quiz> getQuizByCategoryRandomly(int category_id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(QuizHibernateImpl.class);
        criteria.add(Restrictions.eq("category_id", category_id));
        criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
        criteria.setMaxResults(1);
        return criteria.list().size() == 0 ? Optional.empty() : Optional.ofNullable((Quiz) criteria.list().get(0));
    }


}
