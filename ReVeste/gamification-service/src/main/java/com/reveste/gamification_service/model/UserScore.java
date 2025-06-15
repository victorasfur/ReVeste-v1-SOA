package com.reveste.gamification_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer financialIntelligenceScore;
    private Integer totalPoints;
    private Integer level;
    private String currentBadge;
    private LocalDateTime lastUpdated;
}

