package com.reveste.betting_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BetDTO {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String description;
    private LocalDateTime betDate;
}

