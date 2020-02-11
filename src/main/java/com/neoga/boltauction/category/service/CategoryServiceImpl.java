package com.neoga.boltauction.category.service;

import com.neoga.boltauction.category.domain.Category;
import com.neoga.boltauction.category.repository.CategoryRepository;
import com.neoga.boltauction.exception.custom.CCategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(CCategoryNotFoundException::new);
    }


}
