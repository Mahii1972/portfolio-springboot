package com.portfolio.controller;

import com.portfolio.model.Portfolio;
import com.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public ResponseEntity<List<Portfolio>> getPortfolioDetails() {
        return ResponseEntity.ok(portfolioService.getAllPortfolios());
    }
    
    @GetMapping("/full")
    public ResponseEntity<Map<String, Object>> getFullPortfolio() {
        // Temporarily return the raw data instead
        return ResponseEntity.ok(portfolioService.getPortfolioDataRaw());
        // Original implementation: 
        // return ResponseEntity.ok(portfolioService.getFullPortfolio());
    }
    
    @GetMapping("/raw")
    public ResponseEntity<Map<String, Object>> getRawPortfolioData() {
        return ResponseEntity.ok(portfolioService.getPortfolioDataRaw());
    }
}