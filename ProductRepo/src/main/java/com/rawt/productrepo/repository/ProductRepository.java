package com.rawt.productrepo.repository;

import com.rawt.model.ProductShort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductShort,String> {
}
