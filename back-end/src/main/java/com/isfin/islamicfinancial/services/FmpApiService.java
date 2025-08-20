package com.isfin.islamicfinancial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FmpApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${fmp.api.key}")
    private String apiKey;

    @Value("${fmp.api.base-url}")
    private String baseUrl;

    // Example: fetch company profile by ticker symbol
    public String getCompanyProfile(String symbol) {
        String url = baseUrl + "/profile/" + symbol + "?apikey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    // You can add more methods here to consume other endpoints
}
