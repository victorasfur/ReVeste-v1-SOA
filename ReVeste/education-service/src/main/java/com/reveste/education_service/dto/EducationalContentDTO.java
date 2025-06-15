package com.reveste.education_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EducationalContentDTO {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String difficulty;
    private Integer estimatedReadTime;
    private LocalDateTime createdDate;
    private Boolean isActive;
}

