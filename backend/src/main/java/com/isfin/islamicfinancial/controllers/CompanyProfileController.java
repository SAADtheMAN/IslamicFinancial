package com.isfin.islamicfinancial.controllers;

import com.isfin.islamicfinancial.entities.CompanyProfile;
import com.isfin.islamicfinancial.services.CompanyProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fmp")
public class CompanyProfileController {

    private final CompanyProfileService service;

    public CompanyProfileController(CompanyProfileService service) {
        this.service = service;
    }

    @GetMapping("/profile/{symbol}/save")
    public ResponseEntity<CompanyProfile> saveProfile(@PathVariable String symbol) {
        CompanyProfile savedProfile = service.fetchAndSaveProfile(symbol);
        return ResponseEntity.ok(savedProfile);
    }
    @GetMapping("/profile/{symbol}")
    public ResponseEntity<CompanyProfile> getProfile(@PathVariable String symbol) {
        return service.getProfileBySymbol(symbol)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
