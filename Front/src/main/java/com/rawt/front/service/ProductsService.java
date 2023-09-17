package com.rawt.front.service;

import com.rawt.front.config.FeignClients;
import com.rawt.model.ProductShort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class ProductsService {

    private final FeignClients.ProductsClient productApiClient;

    @Autowired
    public ProductsService(FeignClients.ProductsClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    public List<ProductShort> productsGet() {
        ResponseEntity<List<ProductShort>> responseEntity = productApiClient.productsGet();
        if(responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()){
            return responseEntity.getBody();
        } else {
            log.error("Error getting products: {}", responseEntity);
            throw new RuntimeException("Error getting products");
        }
    }

    public ProductShort productsPost(ProductShort product) {
        ResponseEntity<ProductShort> responseEntity = productApiClient.productsPost(product);
        if(responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()){
            return responseEntity.getBody();
        } else {
            log.error("Error posting product: {}", responseEntity);
            throw new RuntimeException("Error posting product");
        }
    }

    public void deleteProductById(String productId) {
        ResponseEntity<Void> responseEntity = productApiClient.productsProductIdDelete(productId);
        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            log.error("Error deleting product: {}", responseEntity);
            throw new RuntimeException("Error deleting product");
        }
    }

    public ProductShort getProductById(String productId) {
        ResponseEntity<ProductShort> responseEntity = productApiClient.productsProductIdGet(productId);
        if(responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()){
            return responseEntity.getBody();
        } else {
            log.error("Error getting product: {}", responseEntity);
            throw new RuntimeException("Error getting product");
        }
    }

    public ProductShort updateProduct(String productId, ProductShort product) {
        ResponseEntity<ProductShort> responseEntity = productApiClient.productsProductIdPut(productId, product);
        if(responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()){
            return responseEntity.getBody();
        } else {
            log.error("Error updating product: {}", responseEntity);
            throw new RuntimeException("Error updating product");
        }
    }
}

