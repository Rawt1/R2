package com.rawt.productrepo.repository;

import com.rawt.productrepo.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image,String> {
}
