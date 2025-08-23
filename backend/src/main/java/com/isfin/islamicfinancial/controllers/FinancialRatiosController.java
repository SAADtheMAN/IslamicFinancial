package com.isfin.islamicfinancial.controllers;

import com.isfin.islamicfinancial.dto.ComplianceResultDTO;
import com.isfin.islamicfinancial.dto.FinancialRatiosDTO;
import com.isfin.islamicfinancial.entities.FinancialRatios;
import com.isfin.islamicfinancial.services.FinancialRatiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/financial-ratios")
public class FinancialRatiosController {

  private final FinancialRatiosService financialRatiosService;

  @Autowired
  public FinancialRatiosController(FinancialRatiosService financialRatiosService) {
    this.financialRatiosService = financialRatiosService;
  }

  @GetMapping
  public ResponseEntity<List<FinancialRatios>> getAllFinancialRatios() {
    List<FinancialRatios> ratios = financialRatiosService.getAllFinancialRatios();
    return ResponseEntity.ok(ratios);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FinancialRatios> getFinancialRatiosById(@PathVariable Long id) {
    Optional<FinancialRatios> ratios = financialRatiosService.getFinancialRatiosById(id);
    return ratios.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<FinancialRatios> createFinancialRatios(@RequestBody FinancialRatios financialRatios) {
    FinancialRatios savedRatios = financialRatiosService.saveFinancialRatios(financialRatios);
    return ResponseEntity.ok(savedRatios);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFinancialRatios(@PathVariable Long id) {
    if (financialRatiosService.getFinancialRatiosById(id).isPresent()) {
      financialRatiosService.deleteFinancialRatios(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Calculated Ratios Endpoint
  @GetMapping("/{id}/calculated")
  public ResponseEntity<FinancialRatiosDTO> getCalculatedRatios(@PathVariable Long id) {
    Optional<FinancialRatios> optionalRatios = financialRatiosService.getFinancialRatiosById(id);
    if (optionalRatios.isPresent()) {
      FinancialRatiosDTO dto = financialRatiosService.calculateRatios(optionalRatios.get());
      return ResponseEntity.ok(dto);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Shariah Compliance Endpoint
  @GetMapping("/{id}/compliance")
  public ResponseEntity<ComplianceResultDTO> checkCompliance(@PathVariable Long id) {
    Optional<FinancialRatios> optionalRatios = financialRatiosService.getFinancialRatiosById(id);
    if (optionalRatios.isPresent()) {
      ComplianceResultDTO dto = financialRatiosService.evaluateCompliance(optionalRatios.get());
      return ResponseEntity.ok(dto);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
