package com.isfin.islamicfinancial.controllers;

import com.isfin.islamicfinancial.services.FinancialDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/financial")
@CrossOrigin(origins = "*")
public class FinancialDataController {

    @Autowired
    private FinancialDataService financialDataService;

    // Single endpoint for full company data + compliance
    @GetMapping("/check/{symbol}")
    public Map<String, Object> getCompanyDataWithCompliance(@PathVariable String symbol) {
        return financialDataService.getCompanyDataWithCompliance(symbol);
    }
}
