package com.isfin.islamicfinancial.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shariah_compliance")
public class ShariahCompliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to the company this compliance record belongs to
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // Reference to the ETF this compliance record belongs to (optional)
    @ManyToOne
    @JoinColumn(name = "etf_id")
    private ETF etf;

    // Compliance status (true if compliant, false if not)
    @Column(name = "is_compliant", nullable = false)
    private boolean compliant;

    // Optional: date of compliance check
    @Column(name = "checked_date")
    private LocalDate checkedDate;

    // Optional: notes or details about compliance
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public ShariahCompliance() {}

    public ShariahCompliance(Company company, ETF etf, boolean compliant, LocalDate checkedDate, String notes) {
        this.company = company;
        this.etf = etf;
        this.compliant = compliant;
        this.checkedDate = checkedDate;
        this.notes = notes;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ETF getEtf() {
        return etf;
    }

    public void setEtf(ETF etf) {
        this.etf = etf;
    }

    public boolean isCompliant() {
        return compliant;
    }

    public void setCompliant(boolean compliant) {
        this.compliant = compliant;
    }

    public LocalDate getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(LocalDate checkedDate) {
        this.checkedDate = checkedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
