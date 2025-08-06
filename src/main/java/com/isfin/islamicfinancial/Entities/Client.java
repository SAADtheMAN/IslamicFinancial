package com.isfin.islamicfinancial.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Watchlist> watchlist;

    // Optional: reports or compliance history

    // Getters and Setters
}
