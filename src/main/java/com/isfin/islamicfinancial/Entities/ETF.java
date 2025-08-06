package com.isfin.islamicfinancial.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ETF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String symbol;
    private String provider;
    private double marketCap;

    @OneToOne(mappedBy = "etf", cascade = CascadeType.ALL)
    private FinancialRatios financialRatios;

    @OneToMany(mappedBy = "etf", cascade = CascadeType.ALL)
    private List<ShariahCompilance> complianceRecords;

    // Optional: Add watchlist support like Company
    // @OneToMany(mappedBy = "etf", cascade = CascadeType.ALL)
    // private List<Watchlist> watchlists;

    // Getters and Setters
}
