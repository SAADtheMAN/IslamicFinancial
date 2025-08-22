package com.isfin.islamicfinancial.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "financial_ratios")
public class FinancialRatios {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Raw financial data needed for calculations
  private double totalDebt;
  private double totalAssets;
  private double currentAssets;
  private double currentLiabilities;
  private double cash;
  private double marketableSecurities;
  private double receivables;
  private double netIncome;
  private double revenue;

  // Existing stored ratios (optional, can keep or remove if calculated dynamically)
  private double debtRatio;
  private double interestBearingAssetRatio;
  private double liquidityRatio;
  private double impermissibleIncomeRatio;
  @Column(name = "avg_market_cap36months", nullable = true)
  private Double avgMarketCap36Months;

  @Column(name = "impermissible_income", nullable = true)
  private Double impermissibleIncome;

  @OneToOne
  @JoinColumn(name = "company_id", unique = true)
  private Company company;

  @OneToOne
  @JoinColumn(name = "etf_id", unique = true)
  private ETF etf;

  public FinancialRatios() {}

  // Getters and setters for all fields below...

  public Long getId() { return id; }

  public double getTotalDebt() { return totalDebt; }
  public void setTotalDebt(double totalDebt) { this.totalDebt = totalDebt; }

  public double getTotalAssets() { return totalAssets; }
  public void setTotalAssets(double totalAssets) { this.totalAssets = totalAssets; }

  public double getCurrentAssets() { return currentAssets; }
  public void setCurrentAssets(double currentAssets) { this.currentAssets = currentAssets; }

  public double getCurrentLiabilities() { return currentLiabilities; }
  public void setCurrentLiabilities(double currentLiabilities) { this.currentLiabilities = currentLiabilities; }

  public double getCash() { return cash; }
  public void setCash(double cash) { this.cash = cash; }

  public double getMarketableSecurities() { return marketableSecurities; }
  public void setMarketableSecurities(double marketableSecurities) { this.marketableSecurities = marketableSecurities; }

  public double getReceivables() { return receivables; }
  public void setReceivables(double receivables) { this.receivables = receivables; }

  public double getNetIncome() { return netIncome; }
  public void setNetIncome(double netIncome) { this.netIncome = netIncome; }

  public double getRevenue() { return revenue; }
  public void setRevenue(double revenue) { this.revenue = revenue; }

  public double getDebtRatio() { return debtRatio; }
  public void setDebtRatio(double debtRatio) { this.debtRatio = debtRatio; }

  public double getInterestBearingAssetRatio() { return interestBearingAssetRatio; }
  public void setInterestBearingAssetRatio(double interestBearingAssetRatio) { this.interestBearingAssetRatio = interestBearingAssetRatio; }

  public double getLiquidityRatio() { return liquidityRatio; }
  public void setLiquidityRatio(double liquidityRatio) { this.liquidityRatio = liquidityRatio; }

  public double getImpermissibleIncomeRatio() { return impermissibleIncomeRatio; }
  public void setImpermissibleIncomeRatio(double impermissibleIncomeRatio) { this.impermissibleIncomeRatio = impermissibleIncomeRatio; }

  public Company getCompany() { return company; }
  public void setCompany(Company company) { this.company = company; }

  public ETF getEtf() { return etf; }
  public void setEtf(ETF etf) { this.etf = etf; }

  public Double getAvgMarketCap36Months() { return avgMarketCap36Months; }
  public void setAvgMarketCap36Months(Double avgMarketCap36Months) { this.avgMarketCap36Months = avgMarketCap36Months; }

  public Double getImpermissibleIncome() { return impermissibleIncome; }
  public void setImpermissibleIncome(Double impermissibleIncome) { this.impermissibleIncome = impermissibleIncome; }

}
