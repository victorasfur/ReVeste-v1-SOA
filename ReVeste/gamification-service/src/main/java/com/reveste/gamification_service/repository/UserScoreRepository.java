package com.reveste.gamification_service.repository;

import com.reveste.gamification_service.model.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    UserScore findByUserId(Long userId);
}

