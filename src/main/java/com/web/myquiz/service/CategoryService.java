package com.web.myquiz.service;

import com.web.myquiz.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAllCategories();

    Optional<Category> getCategoryByName(String name);

    Optional<Category> getCategoryById(int category_id);

}
