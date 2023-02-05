package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.OptionDao;
import com.web.myquiz.domain.Option;
import com.web.myquiz.domain.hibernateImpl.OptionHibernateImpl;
import com.web.myquiz.domain.hibernateImpl.QuestionHibernateImpl;
import com.web.myquiz.domain.hibernateImpl.UserHibernateImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository("optionDaoHibernateImpl")
public class OptionDaoHibernateImpl implements OptionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Option> getOptionsByQuestion(int question_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OptionHibernateImpl> criteria = builder.createQuery(OptionHibernateImpl.class);
        Root<OptionHibernateImpl> root = criteria.from(OptionHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("question_id"), question_id));

        Query<OptionHibernateImpl> query = session.createQuery(criteria);
        List<OptionHibernateImpl> options = query.getResultList();
        return new ArrayList<>(options);
    }

    @Override
    public Optional<Option> getOptionById(int option_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OptionHibernateImpl> criteria = builder.createQuery(OptionHibernateImpl.class);
        Root<OptionHibernateImpl> root = criteria.from(OptionHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("option_id"), option_id));

        Query<OptionHibernateImpl> query = session.createQuery(criteria);
        List<OptionHibernateImpl> options = query.getResultList();
        return options.size() == 0 ? Optional.empty() : Optional.ofNullable(options.get(0));
    }

    @Override
    public void createOptions(int question_id, List<String> contents, int solution) {
        Session session = sessionFactory.getCurrentSession();
        for(int i = 0; i < contents.size(); i++) {
            OptionHibernateImpl option = OptionHibernateImpl.builder()
                    .question_id(question_id)
                    .content(contents.get(i))
                    .is_solution((i==solution-1)?true: false)
                    .build();
            session.save(option);
        }
    }

    @Override
    public void updateOption(int option_id, String content, boolean is_solution) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<OptionHibernateImpl> criteria = builder.createCriteriaUpdate(OptionHibernateImpl.class);
        Root<OptionHibernateImpl> root = criteria.from(OptionHibernateImpl.class);

        criteria.set("content", content)
                .set("is_solution", is_solution)
                .where(builder.equal(root.get("option_id"), option_id));
        session.createQuery(criteria).executeUpdate();
    }


}
