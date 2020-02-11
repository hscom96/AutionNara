package com.neoga.boltauction.category.service;

import com.neoga.boltauction.category.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();
    Category getCategory(Long categoryId);
}
