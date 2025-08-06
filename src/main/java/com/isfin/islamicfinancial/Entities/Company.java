package com.isfin.islamicfinancial.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String sector;
    private String ticker;
    private double marketCap;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private FinancialRatios financialRatios;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<ShariahCompilance> complianceRecords;

    // Getters and Setters
}
