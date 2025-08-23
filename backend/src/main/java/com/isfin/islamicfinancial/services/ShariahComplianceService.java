package com.isfin.islamicfinancial.services;

import com.isfin.islamicfinancial.entities.ShariahCompliance;
import com.isfin.islamicfinancial.repositories.ShariahComplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShariahComplianceService {

    private final ShariahComplianceRepository repository;

    @Autowired
    public ShariahComplianceService(ShariahComplianceRepository repository) {
        this.repository = repository;
    }

    public List<ShariahCompliance> getAllComplianceRecords() {
        return repository.findAll();
    }

    public Optional<ShariahCompliance> getComplianceById(Long id) {
        return repository.findById(id);
    }

    public ShariahCompliance saveComplianceRecord(ShariahCompliance record) {
        return repository.save(record);
    }

    public void deleteComplianceRecord(Long id) {
        repository.deleteById(id);
    }

    public boolean isCompanyCompliant(Long companyId) {
        return repository.existsByCompanyIdAndCompliantTrue(companyId);
    }

    public List<ShariahCompliance> getComplianceRecordsByCompanyId(Long companyId) {
        return repository.findByCompanyId(companyId);
    }
}
