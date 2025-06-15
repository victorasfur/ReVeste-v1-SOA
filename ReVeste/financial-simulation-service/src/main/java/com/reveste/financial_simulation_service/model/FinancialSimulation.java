package com.reveste.financial_simulation_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class FinancialSimulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private BigDecimal monthlyAmount;
    private String investmentType;
    private Double annualRate;
    private Integer years;
    private BigDecimal projectedValue;
    private LocalDateTime simulationDate;
}

