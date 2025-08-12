package com.isfin.islamicfinancial.services;

import com.isfin.islamicfinancial.dto.FinancialRatiosDTO;
import com.isfin.islamicfinancial.entities.FinancialRatios;
import com.isfin.islamicfinancial.repositories.FinancialRatiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialRatiosService {

  private final FinancialRatiosRepository financialRatiosRepository;

  @Autowired
  public FinancialRatiosService(FinancialRatiosRepository financialRatiosRepository) {
    this.financialRatiosRepository = financialRatiosRepository;
  }

  public List<FinancialRatios> getAllFinancialRatios() {
    return financialRatiosRepository.findAll();
  }

  public Optional<FinancialRatios> getFinancialRatiosById(Long id) {
    return financialRatiosRepository.findById(id);
  }

  public FinancialRatios saveFinancialRatios(FinancialRatios financialRatios) {
    return financialRatiosRepository.save(financialRatios);
  }

  public void deleteFinancialRatios(Long id) {
    financialRatiosRepository.deleteById(id);
  }

  // Calculation methods
  public double calculateDebtRatio(double totalDebt, double totalAssets) {
    if (totalAssets == 0) return 0;
    return totalDebt / totalAssets;
  }

  public double calculateCurrentRatio(double currentAssets, double currentLiabilities) {
    if (currentLiabilities == 0) return 0;
    return currentAssets / currentLiabilities;
  }

  public double calculateLiquidityRatio(double cash, double marketableSecurities, double receivables, double currentLiabilities) {
    if (currentLiabilities == 0) return 0;
    return (cash + marketableSecurities + receivables) / currentLiabilities;
  }

  public double calculateProfitabilityRatio(double netIncome, double revenue) {
    if (revenue == 0) return 0;
    return netIncome / revenue;
  }

  // Aggregate calculated ratios into DTO
  public FinancialRatiosDTO calculateRatios(FinancialRatios financialRatios) {
    double debtRatio = calculateDebtRatio(financialRatios.getTotalDebt(), financialRatios.getTotalAssets());
    double currentRatio = calculateCurrentRatio(financialRatios.getCurrentAssets(), financialRatios.getCurrentLiabilities());
    double liquidityRatio = calculateLiquidityRatio(
      financialRatios.getCash(),
      financialRatios.getMarketableSecurities(),
      financialRatios.getReceivables(),
      financialRatios.getCurrentLiabilities()
    );
    double profitabilityRatio = calculateProfitabilityRatio(financialRatios.getNetIncome(), financialRatios.getRevenue());

    return new FinancialRatiosDTO(debtRatio, currentRatio, liquidityRatio, profitabilityRatio);
  }
}
