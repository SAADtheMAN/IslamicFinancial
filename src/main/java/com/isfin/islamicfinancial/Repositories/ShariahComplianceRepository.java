package com.isfin.islamicfinancial.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isfin.islamicfinancial.Entities.ShariahCompilance;
import java.util.List;

public interface ShariahComplianceRepository extends JpaRepository<ShariahCompilance, Long> {
    List<ShariahCompilance> findByCompanyId(Long companyId);
    List<ShariahCompilance> findByEtfId(Long etfId);
}
