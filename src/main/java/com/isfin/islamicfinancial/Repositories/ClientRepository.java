package com.isfin.islamicfinancial.Repositories;

import com.isfin.islamicfinancial.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
