package com.rawt.productrepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ProductRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductRepoApplication.class, args);
    }

}
