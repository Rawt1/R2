package com.rawt.recomender.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.rawt.api.RecommendationsApi;
import com.rawt.model.Recommendation;
import com.rawt.recomender.clients.PersonClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Random;

@Controller
public class MainController implements RecommendationsApi {
    Random random = new Random();

    @Value("${recomender.version}")
    private String recommenderVersion;

    private final PersonClient personClient;

    private final String[] comments = {
            "This product exceeded my expectations. It's simply outstanding!",
            "I can't recommend this product enough. It's a game-changer!",
            "If you're looking for a high-quality product, this is it. It's top-notch.",
            "I'm blown away by the performance of this product. It's fantastic!",
            "I've tried several similar products, but this one takes the crown. It's phenomenal!",
            "I'm in love with this product. It's made my life so much easier.",
            "Trust me, you won't be disappointed. This product is worth every penny.",
            "I can't imagine my routine without this product. It's a must-have.",
            "I've recommended this product to all my friends and family. It's that good!",
            "I'm seriously impressed with this product. It's a winner in every aspect."
    };

    public MainController(PersonClient personClient) {
        this.personClient = personClient;
    }

    @Override
    public ResponseEntity<Recommendation> recommendationsGet(String sessionId) {
//        String person = personClient.getPerson("name");
//        JSONObject personJson = new JSONObject(person);
//        String name = personJson.getJSONArray("results").getJSONObject(0).getJSONObject("name").getString("first");

        String name = "Radosław";
        Recommendation recommendation = new Recommendation()
                .productId((long)random.nextInt(100))
                .explanation(comments[random.nextInt(comments.length)])
                .userName(name)
                .systemName(System.getenv("HOSTNAME") + ":" + recommenderVersion);
        return ResponseEntity.ok(recommendation);
    }



}
