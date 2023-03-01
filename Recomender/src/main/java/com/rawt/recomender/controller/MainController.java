package com.rawt.recomender.controller;

import com.rawt.api.RecommendationsApi;
import com.rawt.model.Recommendation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Random;

@Controller
public class MainController implements RecommendationsApi {
    Random random = new Random();

    @Value("${recomender.version}")
    private String recommenderVersion;

    @Override
    public ResponseEntity<Recommendation> recommendationsGet(String sessionId) {
        Recommendation recommendation = new Recommendation()
                .productId((long)random.nextInt(100))
                .explanation("This is a random recommendation")
                .systemName(System.getenv("HOSTNAME") + ":" + recommenderVersion);
        return ResponseEntity.ok(recommendation);

    }
}
