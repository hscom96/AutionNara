package com.neoga.boltauction.item.service;

import com.neoga.boltauction.category.domain.Category;
import com.neoga.boltauction.category.repository.CategoryRepository;
import com.neoga.boltauction.exception.custom.CCategoryNotFoundException;
import com.neoga.boltauction.exception.custom.CItemNotFoundException;
import com.neoga.boltauction.image.service.ImageService;
import com.neoga.boltauction.item.domain.Item;
import com.neoga.boltauction.item.dto.InsertItemDto;
import com.neoga.boltauction.item.dto.ItemDto;
import com.neoga.boltauction.item.dto.UpdateItemDto;
import com.neoga.boltauction.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageService imageService;

    private static final int NO_CATEGORY = 0;
    private static final int FIRST_CATEGORY = 1;
    private static final int LAST_CATEGORY = 53;

    private static final JSONParser parser = new JSONParser();

    @Override
    public ItemDto getItem(Long id) {

        Item findItem;

        // get item entity
        findItem = itemRepository.findById(id).orElseThrow(CItemNotFoundException::new);

        // map findItem -> itemDto
        ItemDto itemDto = modelMapper.map(findItem, ItemDto.class);
        itemDto.setItemName(findItem.getName());

        return itemDto;
    }

    @Override
    public Item deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(CItemNotFoundException::new);
        itemRepository.delete(item);

        return item;
    }

    @Override
    public Page<ItemDto> getItems(Long categoryId, Pageable pageable) {

        Page<Item> itemPage;

        // get item entity
        if (categoryId == NO_CATEGORY) {
            itemPage = itemRepository.findAll(pageable);
        } else if (categoryId >= FIRST_CATEGORY && categoryId <= LAST_CATEGORY) {
            Category findCategory = categoryRepository.findById(categoryId).orElseThrow(CCategoryNotFoundException::new);
            itemPage = itemRepository.findAllByCategoryEquals(pageable, findCategory);
        } else {
            throw new CCategoryNotFoundException();
        }

        // map item -> itemDto
        return itemPage.map(item -> {
            ItemDto itemDto = modelMapper.map(item, ItemDto.class);
            itemDto.setItemName(item.getName());
            try {
                itemDto.setImagePath((JSONObject) parser.parse(item.getImagePath()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return itemDto;
        });
    }

    @Override
    public ItemDto saveItem(InsertItemDto insertItemDto, MultipartFile... images) throws IOException {
        // map insertItemDto -> item
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Item item = modelMapper.map(insertItemDto, Item.class);
        item.setName(insertItemDto.getItemName());
        item.setCreateDt(LocalDateTime.now());
        Category findCategory = categoryRepository.findById(insertItemDto.getCategoryId()).orElseThrow(CCategoryNotFoundException::new);
        item.setCategory(findCategory);

        String pathList = imageService.saveItemImages(item.getId(), images);
        item.setImagePath(pathList);

        Item saveItem = itemRepository.save(item);

        return mapItemItemDto(saveItem);
    }

    @Override
    public ItemDto updateItem(Long id, UpdateItemDto updateItemDto, MultipartFile[] images) throws IOException {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // find Item
        Item findItem = itemRepository.findById(id).orElseThrow(CItemNotFoundException::new);

        modelMapper.map(updateItemDto, findItem);
        findItem.setName(updateItemDto.getItemName());
        Category findCategory = categoryRepository.findById(updateItemDto.getCategoryId()).orElseThrow(CCategoryNotFoundException::new);
        findItem.setCategory(findCategory);

        // update image
        String path = imageService.updateItemImages(id, images);
        findItem.setImagePath(path);

        // save item
        itemRepository.save(findItem);

        return mapItemItemDto(findItem);
    }

    private ItemDto mapItemItemDto(Item item) {
        ItemDto itemDto = modelMapper.map(item, ItemDto.class);
        itemDto.setItemName(item.getName());
        try {
            itemDto.setImagePath((JSONObject) parser.parse(item.getImagePath()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return itemDto;
    }
}
