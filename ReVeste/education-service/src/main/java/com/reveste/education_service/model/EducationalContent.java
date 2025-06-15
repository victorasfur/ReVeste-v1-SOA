package com.reveste.education_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EducationalContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String category;
    private String difficulty; // BEGINNER, INTERMEDIATE, ADVANCED
    private Integer estimatedReadTime;
    private LocalDateTime createdDate;
    private Boolean isActive;
}

