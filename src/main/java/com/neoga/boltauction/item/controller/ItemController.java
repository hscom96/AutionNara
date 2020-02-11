package com.neoga.boltauction.item.controller;

import com.neoga.boltauction.category.domain.Category;
import com.neoga.boltauction.category.service.CategoryService;
import com.neoga.boltauction.exception.custom.CItemNotFoundException;
import com.neoga.boltauction.image.service.ImageService;
import com.neoga.boltauction.item.domain.Item;
import com.neoga.boltauction.item.dto.InsertItemDto;
import com.neoga.boltauction.item.dto.ItemDto;
import com.neoga.boltauction.item.dto.UpdateItemDto;
import com.neoga.boltauction.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/item")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @GetMapping("category/{category-id}")
    public ResponseEntity getItems(@PathVariable(name = "category-id") Long categoryId, Pageable pageable,
                                   PagedResourcesAssembler<ItemDto> assembler) {

        Page<Item> itemPage;

        try {
            itemPage = itemService.getItems(categoryId, pageable);
        } catch (CItemNotFoundException e) {
            return ResponseEntity.noContent().build();
        }

        Page<ItemDto> itemDtoPage = itemPage.map(item -> {
            ItemDto itemDto = modelMapper.map(item, ItemDto.class);
            itemDto.setItemId(item.getId());
            itemDto.setItemName(item.getName());
            itemDto.setCategoryId(item.getCategory().getId());
            return itemDto;
        });

        PagedModel<EntityModel<ItemDto>> entityModels = assembler.toModel(itemDtoPage, i -> new EntityModel(i));
        entityModels.forEach(entityModel -> entityModel.add(linkTo(methodOn(ItemController.class).getItem(entityModel.getContent().getItemId())).withRel("item-detail")));
        entityModels.add(new Link("/").withRel("profile"));

        return ResponseEntity.ok(entityModels);
    }

    @PostMapping
    public ResponseEntity insertItem(@Valid InsertItemDto insertItemDto,
                                     MultipartFile... files) throws IOException {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Item item = modelMapper.map(insertItemDto, Item.class);
        item.setName(insertItemDto.getItemName());
        item.setCreateDt(LocalDateTime.now());

        Category findCategory = categoryService.getCategory(insertItemDto.getCategoryId());
        item.setCategory(findCategory);

        Item saveItem = itemService.saveItem(item);
        imageService.saveItemImages(saveItem.getId(), files);

        ItemDto saveItemDto = modelMapper.map(saveItem, ItemDto.class);
        saveItemDto.setItemId(saveItem.getId());
        saveItemDto.setItemName(saveItem.getName());
        saveItemDto.setCategoryId(saveItem.getCategory().getId());


        WebMvcLinkBuilder selfLinkBuilder =linkTo(ItemController.class).slash(saveItemDto.getItemId());
        URI createdUri = selfLinkBuilder.toUri();
        EntityModel entityModel = new EntityModel(saveItemDto);

        entityModel.add(linkTo(methodOn(ItemController.class).getItem(saveItemDto.getItemId())).withRel("item-detail"));
        entityModel.add(linkTo(ItemController.class).withRel("query-events"));
        entityModel.add(selfLinkBuilder.withRel("update-event"));
        entityModel.add(new Link("/docs/index.html#resources-events-create").withRel("profile"));

        return ResponseEntity.created(createdUri).body(entityModel);
    }

    @GetMapping("/{item-id}")
    public ResponseEntity getItem(@PathVariable(name = "item-id") Long id) {

        Item findItem;

        try {
            findItem = itemService.getItem(id);
        } catch (CItemNotFoundException e) {
            return ResponseEntity.noContent().build();
        }

        ItemDto itemDto = modelMapper.map(findItem, ItemDto.class);
        itemDto.setItemId(findItem.getId());
        itemDto.setItemName(findItem.getName());
        itemDto.setCategoryId(findItem.getCategory().getId());

        EntityModel entityModel = new EntityModel(itemDto);
        entityModel.add(linkTo(ItemController.class).slash(findItem.getId()).withSelfRel());
        entityModel.add(new Link("/").withRel("profile"));

        return ResponseEntity.ok(entityModel);

    }

    @DeleteMapping("/{item-id}")
    public ResponseEntity deleteItem(@PathVariable(name = "item-id") Long id) {

        try {
            itemService.deleteItem(id);
        } catch (CItemNotFoundException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{item-id}")
    public ResponseEntity updateItem(@PathVariable(name = "item-id") Long id,
                                     @Valid UpdateItemDto updateItemDto,
                                     MultipartFile... files) throws IOException {
        Item findItem;

        try {
            findItem = itemService.getItem(id);
        } catch (CItemNotFoundException e) {
            return ResponseEntity.noContent().build();
        }

        Item updateItem = itemService.updateItem(findItem, updateItemDto);
        imageService.updateItemImages(id, files);

        ItemDto itemDto = modelMapper.map(updateItem, ItemDto.class);
        EntityModel entityModel = new EntityModel(itemDto);
        entityModel.add(linkTo(ItemController.class).slash(updateItem.getId()).withSelfRel());
        entityModel.add(new Link("/").withRel("profile"));

        return ResponseEntity.ok(entityModel);
    }
}
