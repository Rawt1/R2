package com.rawt.front.controllers;

import com.rawt.front.config.FeignClients;
import com.rawt.model.ProductShort;
import com.rawt.model.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {


    private final FeignClients.ProductsClient productsClient;

    @RequestMapping("/admin")
    public String admin(Model model) {
        ResponseEntity<List<ProductShort>> productsResponse = productsClient.productsGet();
        log.info("productsResponse: {}", productsResponse);
        List<ProductShort> products = productsResponse.getBody();
        model.addAttribute("products", products);
        return "admin";
    }
}
