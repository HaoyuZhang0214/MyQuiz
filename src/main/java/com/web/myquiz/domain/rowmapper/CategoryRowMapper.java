package com.web.myquiz.domain.rowmapper;

import com.web.myquiz.domain.Category;
import com.web.myquiz.domain.jdbcImpl.CategoryJdbcImpl;
import com.web.myquiz.domain.jdbcImpl.QuizJdbcImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryJdbcImpl category = new CategoryJdbcImpl();
        category.setCategory_id(rs.getInt("category_id"));
        category.setName(rs.getString("name"));
        return category;
    }
}
