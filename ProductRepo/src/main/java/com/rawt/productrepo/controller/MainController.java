package com.rawt.productrepo.controller;

import com.rawt.api.ProductsApi;
import com.rawt.model.ProductShort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MainController implements ProductsApi {

    List<ProductShort> products = Arrays.asList(
            new ProductShort().title("Product A").shortDescription("Short description of product A").price(19.99f),
            new ProductShort().title("Product B").shortDescription("Short description of product B").price(29.99f),
            new ProductShort().title("Product C").shortDescription("Short description of product C").price(39.99f),
            new ProductShort().title("Product D").shortDescription("Short description of product D").price(49.99f),
            new ProductShort().title("Product E").shortDescription("Short description of product E").price(59.99f)
    );

    @Override
    public ResponseEntity<List<ProductShort>> productsGet() {
        return ResponseEntity.ok(products);
    }
}
