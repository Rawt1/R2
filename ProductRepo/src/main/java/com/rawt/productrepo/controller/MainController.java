package com.rawt.productrepo.controller;

import com.rawt.api.ProductsApi;
import com.rawt.model.ProductShort;
import com.rawt.productrepo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MainController implements ProductsApi {

    private final ProductRepository repository;

    @Override
    public ResponseEntity<List<ProductShort>> productsGet() {
        return ResponseEntity.ok(repository.findAll(PageRequest.of(0,10)).toList());
    }

    @Override
    public ResponseEntity<ProductShort> productsPost(ProductShort productShort) {
        productShort.setId(null);
        repository.save(productShort);
        return ResponseEntity.ok(productShort);
    }

    @Override
    public ResponseEntity<Void> productsProductIdDelete(String productId) {
        repository.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductShort> productsProductIdGet(String productId) {
        Optional<ProductShort> productOpt = repository.findById(productId);
        if(productOpt.isPresent()){
            return new ResponseEntity<>(productOpt.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<ProductShort> productsProductIdPut(String productId, ProductShort productShort) {
        productShort.setId(productId);
        repository.save(productShort);
        return ResponseEntity.ok(productShort);
    }


}