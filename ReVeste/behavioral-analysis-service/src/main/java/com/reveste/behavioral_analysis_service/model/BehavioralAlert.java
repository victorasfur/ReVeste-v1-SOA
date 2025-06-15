package com.reveste.behavioral_analysis_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class BehavioralAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String alertType;
    private String description;
    private String severity; // LOW, MEDIUM, HIGH
    private BigDecimal triggerAmount;
    private LocalDateTime alertDate;
    private Boolean isActive;
}

