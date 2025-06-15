package com.reveste.education_service.repository;

import com.reveste.education_service.model.EducationalContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalContentRepository extends JpaRepository<EducationalContent, Long> {
    List<EducationalContent> findByCategory(String category);
    List<EducationalContent> findByDifficulty(String difficulty);
    List<EducationalContent> findByIsActive(Boolean isActive);
}

