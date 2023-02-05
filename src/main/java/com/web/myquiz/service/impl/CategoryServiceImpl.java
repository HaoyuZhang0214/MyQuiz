package com.web.myquiz.service.impl;

import com.web.myquiz.dao.CategoryDao;
import com.web.myquiz.domain.Category;
import com.web.myquiz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    private final boolean ormMode;

    @Autowired
    public CategoryServiceImpl(@Qualifier("categoryDaoJdbcImpl") CategoryDao categoryDaoJdbcImpl,
                               @Qualifier("categoryDaoHibernateImpl") CategoryDao categoryDaoHibernateImpl,
                               @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.categoryDao = ormMode ? categoryDaoHibernateImpl : categoryDaoJdbcImpl;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }

    @Override
    public Optional<Category> getCategoryById(int category_id) {
        return categoryDao.getCategoryById(category_id);
    }

}
