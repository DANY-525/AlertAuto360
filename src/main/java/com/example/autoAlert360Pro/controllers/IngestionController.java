package com.example.autoAlert360Pro.controllers;

import com.example.autoAlert360Pro.services.IngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RequestMapping("/api/ingestion")
@RestController
public class IngestionController {

    @Autowired
    private IngestionService ingestionService;

    @PostMapping("/send")
    public String sendData() {
        // Get the scraped content from the service
        String scrapedContent = ingestionService.scrapeWebsite();
        String fileName = "scraped_content.html";

        // Encode the content as Base64
        String base64Data = Base64.getEncoder().encodeToString(scrapedContent.getBytes());

        // Prepare the request payload
        String nestUrl = "http://localhost:3000/api/processing/save-file";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonPayload = String.format("{\"fileName\":\"%s\", \"fileData\":\"%s\"}", fileName, base64Data);
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        // Send the POST request to NestJS microservice
        String response = restTemplate.exchange(nestUrl, HttpMethod.POST, request, String.class).getBody();

        return "Response from NestJS: " + response;
    }
}