package com.rawt.productrepo.controller;

import com.rawt.api.ProductsApi;
import com.rawt.model.ProductShort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController implements ProductsApi {

    List<ProductShort> products = Arrays.asList(
            new ProductShort().id("1").title("Product A").shortDescription("Short description of product A").price(19.99f),
            new ProductShort().id("2").title("Product B").shortDescription("Short description of product B").price(29.99f),
            new ProductShort().id("3").title("Product C").shortDescription("Short description of product C").price(39.99f),
            new ProductShort().id("4").title("Product D").shortDescription("Short description of product D").price(49.99f),
            new ProductShort().id("5").title("Product E").shortDescription("Short description of product E").price(59.99f)
    );

    @Override
    public ResponseEntity<List<ProductShort>> productsGet() {
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<ProductShort> productsPost(ProductShort productShort) {
        products.add(productShort);
        return ResponseEntity.ok(productShort);
    }

    @Override
    public ResponseEntity<Void> productsProductIdDelete(String productId) {
        return null;
    }

    @Override
    public ResponseEntity<ProductShort> productsProductIdGet(String productId) {
        Optional<ProductShort> productOpt = products.stream().filter(productShort -> productShort.getId().equals(productId)).findFirst();
        if(productOpt.isPresent()){
            return new ResponseEntity<>(productOpt.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<ProductShort> productsProductIdPut(String productId, ProductShort productShort) {
        productShort.setId(productId);
        for (int i = 0; i <products.size(); i++) {
            if(products.get(i).getId().equals(productId)){
                products.remove(i);
                products.add(productShort);
                break;
            }
        }
        return ResponseEntity.ok(productShort);
    }
}