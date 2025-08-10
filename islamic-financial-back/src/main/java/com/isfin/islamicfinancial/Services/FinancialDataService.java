package com.isfin.islamicfinancial.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class FinancialDataService {

    @Value("${fmp.api.key}")
    private String apiKey;

    @Value("${fmp.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getCompanyDataWithCompliance(String symbol) {
        // 1. Fetch company profile
        String profileUrl = baseUrl + "profile/" + symbol + "?apikey=" + apiKey;
        Object[] profileResponse = restTemplate.getForObject(profileUrl, Object[].class);

        // 2. Fetch financial ratios
        String ratiosUrl = baseUrl + "ratios/" + symbol + "?apikey=" + apiKey;
        Object[] ratiosResponse = restTemplate.getForObject(ratiosUrl, Object[].class);

        // 3. Prepare response map
        Map<String, Object> result = new HashMap<>();
        result.put("profile", profileResponse != null && profileResponse.length > 0 ? profileResponse[0] : null);
        result.put("ratios", ratiosResponse != null && ratiosResponse.length > 0 ? ratiosResponse[0] : null);

        // 4. Compliance check
        String complianceStatus = checkShariahCompliance(ratiosResponse);
        result.put("shariahCompliance", complianceStatus);

        return result;
    }

    private String checkShariahCompliance(Object[] ratiosResponse) {
        if (ratiosResponse == null || ratiosResponse.length == 0) return "Unknown";

        Map<String, Object> ratios = (Map<String, Object>) ratiosResponse[0];

        double debtRatio = ratios.get("debtRatio") != null ? Double.parseDouble(ratios.get("debtRatio").toString()) : 0;
        double interestIncome = ratios.get("interestCoverage") != null ? Double.parseDouble(ratios.get("interestCoverage").toString()) : 0;

        // Example Shariah criteria
        boolean halalDebt = debtRatio < 0.33;
        boolean halalInterest = interestIncome < 0.05;

        if (halalDebt && halalInterest) {
            return "✅ Halal";
        } else {
            return "❌ Not Halal";
        }
    }
}
