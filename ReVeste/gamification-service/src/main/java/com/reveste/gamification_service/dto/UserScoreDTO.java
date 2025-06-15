package com.reveste.gamification_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserScoreDTO {
    private Long id;
    private Long userId;
    private Integer financialIntelligenceScore;
    private Integer totalPoints;
    private Integer level;
    private String currentBadge;
    private LocalDateTime lastUpdated;
}

