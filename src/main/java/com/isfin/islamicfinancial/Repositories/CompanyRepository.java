package com.isfin.islamicfinancial.Repositories;

import com.isfin.islamicfinancial.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByTicker(String ticker);
    boolean existsByTicker(String ticker);
}
