package com.rawt.front.controllers;

import com.rawt.front.config.FeignClients;
import com.rawt.model.ProductShort;
import com.rawt.model.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final FeignClients.ProductsClient productsClient;
    private final FeignClients.RecommendationsClient recommendationsClient;

    @RequestMapping("/")
    public String index(Model model) {
        ResponseEntity<List<ProductShort>> productsResponse = productsClient.productsGet();
        ResponseEntity<Recommendation> recommendationsResponse = recommendationsClient.recommendationsGet("foo");
        log.info("productsResponse: {}", productsResponse);
        log.info("recommendationsResponse: {}", recommendationsResponse);
        List<ProductShort> products = productsResponse.getBody();
        model.addAttribute("products", products);
        model.addAttribute("recommendation", recommendationsResponse.getBody());
        return "index";
    }
}
