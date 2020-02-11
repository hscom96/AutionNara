package com.neoga.boltauction.item.repository;

import com.neoga.boltauction.category.domain.Category;
import com.neoga.boltauction.item.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByOrderByCreateDtAsc(Pageable pageable);
    Page<Item> findAllByCategoryEquals(Pageable pageable, Category category);
}
