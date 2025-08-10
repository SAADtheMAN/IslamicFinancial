package com.isfin.islamicfinancial.repositories;

import com.isfin.islamicfinancial.entities.ShariahCompliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShariahComplianceRepository extends JpaRepository<ShariahCompliance, Long> {

    // Optional: find all compliance records for a specific company
    List<ShariahCompliance> findByCompanyId(Long companyId);

    // Optional: check if a company has any compliant record
    boolean existsByCompanyIdAndCompliantTrue(Long companyId);
}
