package com.rawt.productrepo.controller;

import com.rawt.api.ImagesApi;
import com.rawt.productrepo.model.Image;
import com.rawt.productrepo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImagesController implements ImagesApi {

    private final ImageRepository repository;
    @Override
    public ResponseEntity<Resource> imagesImageIdGet(String imageId) {
        Optional<Image> imageOptional = repository.findById(imageId);
        return imageOptional.<ResponseEntity<Resource>>map(image -> new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(image.getImage().getData())), org.springframework.http.HttpStatus.OK)).orElseGet(() -> ResponseEntity.internalServerError().body(null));
    }

    @Override
    public ResponseEntity<String> imagesPost(MultipartFile file) {
        Image image = new Image();
        try {
            image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Couldn't convert file to binary");
        }
        Image saved = repository.save(image);
        log.info("image saved with id: {}",image.getId());
        return ResponseEntity.ok(saved.getId());
    }
}
