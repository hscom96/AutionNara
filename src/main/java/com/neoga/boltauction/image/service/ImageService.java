package com.neoga.boltauction.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveItemImages(Long itemId, MultipartFile... files) throws IOException;
    String updateItemImages(Long itemId, MultipartFile... files) throws IOException;
}
