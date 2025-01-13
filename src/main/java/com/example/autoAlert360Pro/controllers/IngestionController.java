package com.example.autoAlert360Pro.config;

import com.example.autoAlert360Pro.services.IngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngestionController {

    @Autowired
    private IngestionService ingestionService;

    @PostMapping("/sendData")
    public String sendData(@RequestBody Object data) {
        return ingestionService.scrapeWebsite();
    }
}
