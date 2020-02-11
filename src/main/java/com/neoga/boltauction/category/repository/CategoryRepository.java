package com.neoga.boltauction.category.repository;

import com.neoga.boltauction.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
