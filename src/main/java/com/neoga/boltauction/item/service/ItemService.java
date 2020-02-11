package com.neoga.boltauction.item.service;

import com.neoga.boltauction.item.domain.Item;
import com.neoga.boltauction.item.dto.UpdateItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    Item getItem(Long id);

    Item deleteItem(Long id);

    Page<Item> getItems(Long categoryId, Pageable pageable);

    Item saveItem(Item item);

    Item updateItem(Item item, UpdateItemDto updateItemDto);
}
