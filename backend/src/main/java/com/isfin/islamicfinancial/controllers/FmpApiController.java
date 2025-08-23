package com.isfin.islamicfinancial.controllers;

import com.isfin.islamicfinancial.services.FmpApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FmpApiController {

    @Autowired
    private FmpApiService fmpApiService;

    @GetMapping("/api/fmp/raw-profile/{symbol}")
    public String getProfile(@PathVariable String symbol) {
        return fmpApiService.getCompanyProfile(symbol);
    }
}
