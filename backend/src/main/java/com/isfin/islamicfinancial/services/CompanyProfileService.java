package com.isfin.islamicfinancial.services;


import com.isfin.islamicfinancial.entities.CompanyProfile;
import com.isfin.islamicfinancial.repositories.CompanyProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompanyProfileService {

    private final CompanyProfileRepository repository;
    private final RestTemplate restTemplate;

    @Value("${fmp.api.base-url}")
    private String fmpApiBaseUrl;

    @Value("${fmp.api.key}")
    private String fmpApiKey;

    public CompanyProfileService(CompanyProfileRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public CompanyProfile fetchAndSaveProfile(String symbol) {
        String url = fmpApiBaseUrl + "profile/" + symbol + "?apikey=" + fmpApiKey;
        ResponseEntity<CompanyProfile[]> response = restTemplate.getForEntity(url, CompanyProfile[].class);

        CompanyProfile[] profiles = response.getBody();

        if (profiles != null && profiles.length > 0) {
            CompanyProfile profile = profiles[0];
            return repository.save(profile);
        } else {
            throw new RuntimeException("Profile not found for symbol: " + symbol);
        }
    }
    public java.util.Optional<CompanyProfile> getProfileBySymbol(String symbol) {
        return repository.findById(symbol);
    }
}
