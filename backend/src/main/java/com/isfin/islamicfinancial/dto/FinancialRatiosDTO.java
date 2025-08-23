package com.isfin.islamicfinancial.dto;

public class FinancialRatiosDTO {
  private double debtRatio;
  private double currentRatio;
  private double liquidityRatio;
  private double profitabilityRatio;

  public FinancialRatiosDTO() {}

  public FinancialRatiosDTO(double debtRatio, double currentRatio, double liquidityRatio, double profitabilityRatio) {
    this.debtRatio = debtRatio;
    this.currentRatio = currentRatio;
    this.liquidityRatio = liquidityRatio;
    this.profitabilityRatio = profitabilityRatio;
  }

  public double getDebtRatio() { return debtRatio; }
  public void setDebtRatio(double debtRatio) { this.debtRatio = debtRatio; }

  public double getCurrentRatio() { return currentRatio; }
  public void setCurrentRatio(double currentRatio) { this.currentRatio = currentRatio; }

  public double getLiquidityRatio() { return liquidityRatio; }
  public void setLiquidityRatio(double liquidityRatio) { this.liquidityRatio = liquidityRatio; }

  public double getProfitabilityRatio() { return profitabilityRatio; }
  public void setProfitabilityRatio(double profitabilityRatio) { this.profitabilityRatio = profitabilityRatio; }
}
