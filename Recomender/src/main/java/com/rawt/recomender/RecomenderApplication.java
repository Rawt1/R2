package com.rawt.recomender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RecomenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecomenderApplication.class, args);
    }

}
