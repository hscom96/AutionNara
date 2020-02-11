package com.neoga.boltauction.item;

import com.neoga.boltauction.category.repository.CategoryRepository;
import com.neoga.boltauction.item.domain.Item;
import com.neoga.boltauction.item.repository.ItemRepository;
import com.neoga.boltauction.item.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /*@Test
    public void initItemTest(){
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemList.add(new Item());
        }

        for (int i = 0; i < 10; i++) {
            itemList.get(i).setName("name" + i);
            itemList.get(i).setDescription("description" + i);
            itemList.get(i).setSell(false);
            itemList.get(i).setQuickPrice((i + 1) * 10000);
            itemList.get(i).setMinBidPrice((i + 1) * 100);
            itemList.get(i).setStartPrice((i + 1) * 1000);
            itemList.get(i).setCategory(categoryRepository.findById((long) ((i % 3) + 1)).get());
        }

        itemList.stream().forEach(item -> itemRepository.save(item));
    }*/

    @Test
    public void getItemTest(){
        //given

    }

    @Test
    public void deleteItemTest(){

    }

    @Test
    public void getItemsTest(){

    }



    @Test
    public void updateItemTest(){

    }
}
