package com.reveste.behavioral_analysis_service.repository;

import com.reveste.behavioral_analysis_service.model.BehavioralAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BehavioralAlertRepository extends JpaRepository<BehavioralAlert, Long> {
    List<BehavioralAlert> findByUserId(Long userId);
    List<BehavioralAlert> findByUserIdAndIsActive(Long userId, Boolean isActive);
}

