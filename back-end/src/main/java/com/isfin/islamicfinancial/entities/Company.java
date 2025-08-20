package com.isfin.islamicfinancial.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String sector;
    private String ticker;
    private double marketCap;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private FinancialRatios financialRatios;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShariahCompliance> complianceRecords;

    // Default constructor
    public Company() {}

    // Constructor without collections
    public Company(String name, String sector, String ticker, double marketCap) {
        this.name = name;
        this.sector = sector;
        this.ticker = ticker;
        this.marketCap = marketCap;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public FinancialRatios getFinancialRatios() {
        return financialRatios;
    }

    public void setFinancialRatios(FinancialRatios financialRatios) {
        this.financialRatios = financialRatios;
    }

    public List<ShariahCompliance> getComplianceRecords() {
        return complianceRecords;
    }

    public void setComplianceRecords(List<ShariahCompliance> complianceRecords) {
        this.complianceRecords = complianceRecords;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", ticker='" + ticker + '\'' +
                ", marketCap=" + marketCap +
                '}';
    }
}
