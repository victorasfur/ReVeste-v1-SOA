package com.reveste.behavioral_analysis_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BehavioralAlertDTO {
    private Long id;
    private Long userId;
    private String alertType;
    private String description;
    private String severity;
    private BigDecimal triggerAmount;
    private LocalDateTime alertDate;
    private Boolean isActive;
}

