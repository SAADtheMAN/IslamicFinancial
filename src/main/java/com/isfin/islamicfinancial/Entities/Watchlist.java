package com.isfin.islamicfinancial.Entities;

import jakarta.persistence.*;

@Entity
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String note; // Optional: user notes

    // Getters and Setters
}
