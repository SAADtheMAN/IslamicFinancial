package com.isfin.islamicfinancial.Entities;

import jakarta.persistence.*;

@Entity
public class ShariahCompilance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String standardUsed;  // e.g. AAOIFI, MSCI, S&P
    private boolean isCompliant;
    private String explanation;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "etf_id")
    private ETF etf;

}
