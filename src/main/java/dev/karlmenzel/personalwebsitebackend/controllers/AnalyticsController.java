package dev.karlmenzel.personalwebsitebackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.karlmenzel.personalwebsitebackend.processors.AnalyticsProcessor;

@RestController
public class AnalyticsController {

    private final AnalyticsProcessor analyticsProcessor;

    @Autowired
    public AnalyticsController(AnalyticsProcessor ap) {
        analyticsProcessor = ap;
    }

    @CrossOrigin(origins = {"http://localhost", "http://localhost:3000"})
    @GetMapping("/site-visits")
    public String getSiteVisitsCount() {
        return analyticsProcessor.getSiteVisitsCount();
    }
}