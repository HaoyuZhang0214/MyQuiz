package com.web.myquiz.dao.hibernateImpl;

import com.web.myquiz.dao.CategoryDao;
import com.web.myquiz.domain.Category;
import com.web.myquiz.domain.hibernateImpl.CategoryHibernateImpl;
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
@Repository("categoryDaoHibernateImpl")
public class CategoryDaoHibernateImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    public CategoryDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CategoryHibernateImpl> criteria = builder.createQuery(CategoryHibernateImpl.class);
        Root<CategoryHibernateImpl> root = criteria.from(CategoryHibernateImpl.class);
        criteria.select(root);

        Query<CategoryHibernateImpl> query = session.createQuery(criteria);
        List<CategoryHibernateImpl> categories = query.getResultList();
        return new ArrayList<>(categories);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CategoryHibernateImpl> criteria = builder.createQuery(CategoryHibernateImpl.class);
        Root<CategoryHibernateImpl> root = criteria.from(CategoryHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("name"), name));

        Query<CategoryHibernateImpl> query = session.createQuery(criteria);
        List<CategoryHibernateImpl> categories = query.getResultList();
        return categories.size() == 0 ? Optional.empty(): Optional.ofNullable(categories.get(0));
    }

    @Override
    public Optional<Category> getCategoryById(int category_id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CategoryHibernateImpl> criteria = builder.createQuery(CategoryHibernateImpl.class);
        Root<CategoryHibernateImpl> root = criteria.from(CategoryHibernateImpl.class);
        criteria.select(root)
                .where(builder.equal(root.get("category_id"), category_id));

        Query<CategoryHibernateImpl> query = session.createQuery(criteria);
        List<CategoryHibernateImpl> categories = query.getResultList();
        return categories.size() == 0 ? Optional.empty(): Optional.ofNullable(categories.get(0));
    }

}
