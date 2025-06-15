package com.reveste.financial_simulation_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FinancialSimulationDTO {
    private Long id;
    private Long userId;
    private BigDecimal monthlyAmount;
    private String investmentType;
    private Double annualRate;
    private Integer years;
    private BigDecimal projectedValue;
    private LocalDateTime simulationDate;
}

