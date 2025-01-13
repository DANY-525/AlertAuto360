package com.example.autoAlert360Pro.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class IngestionService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String NESTJS_URL = "http://localhost:3000/api/scraping/static"; // URL de NestJS
    private static final String SCRAPPING_URL = "https://www.mercadolibre.com.co/"; // URL de NestJS

    public String scrapeWebsite() {
        // Construct the full URL by appending the query parameter
        String fullUrl = NESTJS_URL + "?url=" + SCRAPPING_URL;
        // Send a GET request to the NestJS scraping endpoint
        ResponseEntity<String> response = restTemplate.exchange(fullUrl, HttpMethod.GET, null, String.class);
        return response.getBody();
    }
}
