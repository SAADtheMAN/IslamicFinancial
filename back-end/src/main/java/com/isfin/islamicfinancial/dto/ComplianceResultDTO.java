package com.isfin.islamicfinancial.dto;

public class ComplianceResultDTO {
  private boolean debtRatioCompliant;
  private boolean interestBearingAssetCompliant;
  private boolean liquidityRatioCompliant;
  private boolean impermissibleIncomeCompliant;

  public boolean isDebtRatioCompliant() {
    return debtRatioCompliant;
  }

  public void setDebtRatioCompliant(boolean debtRatioCompliant) {
    this.debtRatioCompliant = debtRatioCompliant;
  }

  public boolean isInterestBearingAssetCompliant() {
    return interestBearingAssetCompliant;
  }

  public void setInterestBearingAssetCompliant(boolean interestBearingAssetCompliant) {
    this.interestBearingAssetCompliant = interestBearingAssetCompliant;
  }

  public boolean isLiquidityRatioCompliant() {
    return liquidityRatioCompliant;
  }

  public void setLiquidityRatioCompliant(boolean liquidityRatioCompliant) {
    this.liquidityRatioCompliant = liquidityRatioCompliant;
  }

  public boolean isImpermissibleIncomeCompliant() {
    return impermissibleIncomeCompliant;
  }

  public void setImpermissibleIncomeCompliant(boolean impermissibleIncomeCompliant) {
    this.impermissibleIncomeCompliant = impermissibleIncomeCompliant;
  }
}
