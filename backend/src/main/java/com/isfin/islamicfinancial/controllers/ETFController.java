package com.isfin.islamicfinancial.controllers;

import com.isfin.islamicfinancial.entities.ETF;
import com.isfin.islamicfinancial.services.ETFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etf")
public class ETFController {

    private final ETFService etfService;

    @Autowired
    public ETFController(ETFService etfService) {
        this.etfService = etfService;
    }

    // Get all ETFs
    @GetMapping
    public ResponseEntity<List<ETF>> getAllETFs() {
        List<ETF> etfs = etfService.getAllETFs();
        return ResponseEntity.ok(etfs);
    }

    // Get ETF by ID
    @GetMapping("/{id}")
    public ResponseEntity<ETF> getETFById(@PathVariable Long id) {
        Optional<ETF> etf = etfService.getETFById(id);
        return etf.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new ETF
    @PostMapping
    public ResponseEntity<ETF> createETF(@RequestBody ETF etf) {
        ETF savedETF = etfService.saveETF(etf);
        return ResponseEntity.ok(savedETF);
    }

    // Delete ETF by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteETF(@PathVariable Long id) {
        if (etfService.getETFById(id).isPresent()) {
            etfService.deleteETF(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
