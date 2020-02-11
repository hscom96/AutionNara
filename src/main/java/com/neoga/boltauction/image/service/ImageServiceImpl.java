package com.neoga.boltauction.image.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String saveItemImages(Long itemId, MultipartFile... files) throws IOException {

        for (MultipartFile file : files) {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            if (bufferedImage == null) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("src/main/resources/image/" + itemId + "/" + file.getOriginalFilename());
                Files.write(path, bytes);
            } else {
                return null;
            }
        }

        return "ok";
    }

    @Override
    public String updateItemImages(Long itemId, MultipartFile... files) throws IOException {
        return null;
    }
}
