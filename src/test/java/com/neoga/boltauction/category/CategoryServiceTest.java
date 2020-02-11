package com.neoga.boltauction.category;

import com.neoga.boltauction.category.domain.Category;
import com.neoga.boltauction.category.repository.CategoryRepository;
import com.neoga.boltauction.category.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void initCategory() throws Exception {
        //given
        Category category1 = new Category();
        category1.setName("category1");
        Category category2 = new Category();
        category2.setName("category2");
        Category category3 = new Category();
        category3.setName("category3");

        //when
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        //then
        System.out.println(categoryRepository.findAll().toString());
    }

    @Test
    public void getCategoryListTest() throws Exception {
        //given
        List<Category> categoryList = categoryService.getCategoryList();

        //when

        //then
        categoryList.forEach(category -> System.out.println(category.getName()));
    }


}
