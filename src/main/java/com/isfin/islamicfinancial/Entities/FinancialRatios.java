package com.isfin.islamicfinancial.Entities;

import jakarta.persistence.*;

@Entity
public class FinancialRatios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double debtRatio;
    private double interestBearingAssetRatio;
    private double liquidityRatio;
    private double impermissibleIncomeRatio;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "etf_id")
    private ETF etf;

}