package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.QuestionDao;
import com.web.myquiz.domain.Question;
import com.web.myquiz.domain.User;
import com.web.myquiz.domain.hibernateImpl.FeedbackHibernateImpl;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository("questionDaoHibernateImpl")
public class QuestionDaoHibernateImpl implements QuestionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Question> getQuestionsByQuiz(int quiz_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionHibernateImpl> criteria = builder.createQuery(QuestionHibernateImpl.class);
        Root<QuestionHibernateImpl> root = criteria.from(QuestionHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("quiz_id"), quiz_id), builder.equal(root.get("status"), true));

        Query<QuestionHibernateImpl> query = session.createQuery(criteria);
        List<QuestionHibernateImpl> questions = query.getResultList();
        return new ArrayList<>(questions);
    }

    @Override
    public List<Question> getAllQuestions() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionHibernateImpl> criteria = builder.createQuery(QuestionHibernateImpl.class);
        Root<QuestionHibernateImpl> root = criteria.from(QuestionHibernateImpl.class);
        criteria.select(root);

        Query<QuestionHibernateImpl> query = session.createQuery(criteria);
        List<QuestionHibernateImpl> questions = query.getResultList();
        return new ArrayList<>(questions);
    }

    @Override
    public void updateQuestionStatus(int question_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<QuestionHibernateImpl> criteria = builder.createCriteriaUpdate(QuestionHibernateImpl.class);
        Root<QuestionHibernateImpl> root = criteria.from(QuestionHibernateImpl.class);

        Optional<Question> question = getQuestionById(question_id);
        boolean status = true;
        if(question.isPresent()) {
            status = question.get().isStatus()? false: true;
        }
        criteria.set("status", status)
                .where(builder.equal(root.get("question_id"), question_id));
        session.createQuery(criteria).executeUpdate();
    }

    @Override
    public Optional<Question> getQuestionById(int question_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionHibernateImpl> criteria = builder.createQuery(QuestionHibernateImpl.class);
        Root<QuestionHibernateImpl> root = criteria.from(QuestionHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("question_id"), question_id));

        Query<QuestionHibernateImpl> query = session.createQuery(criteria);
        List<QuestionHibernateImpl> questions = query.getResultList();
        return questions.size()==0? Optional.empty(): Optional.ofNullable(questions.get(0));
    }

    @Override
    public void addQuestion(int quiz_id, String content) {
        Session session = sessionFactory.getCurrentSession();
        QuestionHibernateImpl question = QuestionHibernateImpl.builder()
                .quiz_id(quiz_id)
                .content(content)
                .status(true)
                .build();
        session.save(question);
    }

    @Override
    public void updateQuestionContent(String content, int question_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<QuestionHibernateImpl> criteria = builder.createCriteriaUpdate(QuestionHibernateImpl.class);
        Root<QuestionHibernateImpl> root = criteria.from(QuestionHibernateImpl.class);

        criteria.set("content", content)
                .where(builder.equal(root.get("question_id"), question_id));
        session.createQuery(criteria).executeUpdate();
    }
}
