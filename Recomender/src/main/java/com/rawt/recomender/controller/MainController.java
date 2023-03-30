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

    public MainController(PersonClient personClient) {
        this.personClient = personClient;
    }

    @Override
    public ResponseEntity<Recommendation> recommendationsGet(String sessionId) {
        String person = personClient.getPerson("name");
        JSONObject personJson = new JSONObject(person);
        String name = personJson.getJSONArray("results").getJSONObject(0).getJSONObject("name").getString("first");

        Recommendation recommendation = new Recommendation()
                .productId((long)random.nextInt(100))
                .explanation("This is a recommendation for " + name)
                .systemName(System.getenv("HOSTNAME") + ":" + recommenderVersion);
        return ResponseEntity.ok(recommendation);
    }
}
