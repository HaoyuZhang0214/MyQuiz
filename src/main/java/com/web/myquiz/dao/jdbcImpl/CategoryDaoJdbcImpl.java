package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.CategoryDao;
import com.web.myquiz.domain.Category;
import com.web.myquiz.domain.rowmapper.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("categoryDaoJdbcImpl")
public class CategoryDaoJdbcImpl implements CategoryDao {

    JdbcTemplate jdbcTemplate;
    CategoryRowMapper categoryRowMapper;

    @Autowired
    public CategoryDaoJdbcImpl(JdbcTemplate jdbcTemplate, CategoryRowMapper categoryRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRowMapper = categoryRowMapper;
    }

    @Override
    public List<Category> getAllCategories() {
        String query = "SELECT * FROM Category";
        List<Category> categories =  jdbcTemplate.query(query, categoryRowMapper);
        return categories;
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        String query = "SELECT * FROM Category WHERE name = ?";
        List<Category> categories =  jdbcTemplate.query(query, categoryRowMapper, name);
        return categories.size() == 0 ? Optional.empty() : Optional.ofNullable(categories.get(0));
    }

    @Override
    public Optional<Category> getCategoryById(int category_id) {
        String query = "SELECT * FROM Category WHERE category_id = ?";
        List<Category> categories =  jdbcTemplate.query(query, categoryRowMapper, category_id);
        return categories.size() == 0 ? Optional.empty() : Optional.ofNullable(categories.get(0));
    }
}
