package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.UserDao;
import com.web.myquiz.domain.User;
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
@Repository("userDaoHibernateImpl")
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> getValidUser(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserHibernateImpl> criteria = builder.createQuery(UserHibernateImpl.class);
        Root<UserHibernateImpl> root = criteria.from(UserHibernateImpl.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("username"), username), builder.equal(root.get("password"), password), builder.equal(root.get("status"), true));

        Query<UserHibernateImpl> query = session.createQuery(criteria);
        List<UserHibernateImpl> users = query.getResultList();
        return users.size() == 0 ? Optional.empty() : Optional.ofNullable(users.get(0));
    }

    @Override
    public void createUser(String username, String password, String firstname, String lastname) {
        Session session = sessionFactory.getCurrentSession();
        UserHibernateImpl user = UserHibernateImpl.builder()
                .username(username)
                .password(password)
                .firstname(firstname)
                .lastname(lastname)
                .status(true)
                .is_admin(false)
                .build();
        session.save(user);
    }

    @Override
    public Optional<User> getUserById(int user_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserHibernateImpl> criteria = builder.createQuery(UserHibernateImpl.class);
        Root<UserHibernateImpl> root = criteria.from(UserHibernateImpl.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("user_id"), user_id));

        Query<UserHibernateImpl> query = session.createQuery(criteria);
        List<UserHibernateImpl> users = query.getResultList();
        return users.size() == 0 ? Optional.empty() : Optional.ofNullable(users.get(0));
    }

    @Override
    public void updateUserAccount(int user_id, String password, String firstname, String lastname, String address, String email, String phone) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<UserHibernateImpl> criteria = builder.createCriteriaUpdate(UserHibernateImpl.class);
        Root<UserHibernateImpl> root = criteria.from(UserHibernateImpl.class);
        criteria.set("password", password)
                .set("firstname", firstname)
                .set("lastname", lastname)
                .set("address", address)
                .set("email", email)
                .set("phone", phone)
                .where(builder.equal(root.get("user_id"), user_id));
        session.createQuery(criteria).executeUpdate();
    }

    @Override
    public List<User> getAllUsersExceptAdmin() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserHibernateImpl> criteria = builder.createQuery(UserHibernateImpl.class);
        Root<UserHibernateImpl> root = criteria.from(UserHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("is_admin"), false));

        Query<UserHibernateImpl> query = session.createQuery(criteria);
        List<UserHibernateImpl> users = query.getResultList();
        return new ArrayList<>(users);
    }

    @Override
    public void updateUserStatus(int user_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<UserHibernateImpl> criteria = builder.createCriteriaUpdate(UserHibernateImpl.class);
        Root<UserHibernateImpl> root = criteria.from(UserHibernateImpl.class);

        Optional<User> user = getUserById(user_id);
        boolean status = true;
        if(user.isPresent()) {
            status = user.get().isStatus()? false: true;
        }
        criteria.set("status", status)
                .where(builder.equal(root.get("user_id"), user_id));
        session.createQuery(criteria).executeUpdate();
    }

}
