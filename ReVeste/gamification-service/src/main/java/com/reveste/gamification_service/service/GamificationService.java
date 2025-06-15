package com.reveste.gamification_service.service;

import com.reveste.gamification_service.dto.UserScoreDTO;
import com.reveste.gamification_service.model.UserScore;
import com.reveste.gamification_service.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamificationService {

    @Autowired
    private UserScoreRepository userScoreRepository;

    public List<UserScoreDTO> getAllUserScores() {
        return userScoreRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserScoreDTO getUserScoreByUserId(Long userId) {
        UserScore userScore = userScoreRepository.findByUserId(userId);
        if (userScore == null) {
            // Criar score inicial para o usuário
            userScore = createInitialUserScore(userId);
        }
        return convertToDTO(userScore);
    }

    public UserScoreDTO updateUserScore(Long userId, Integer pointsToAdd) {
        UserScore userScore = userScoreRepository.findByUserId(userId);
        if (userScore == null) {
            userScore = createInitialUserScore(userId);
        }
        
        userScore.setTotalPoints(userScore.getTotalPoints() + pointsToAdd);
        userScore.setFinancialIntelligenceScore(calculateFinancialIntelligenceScore(userScore.getTotalPoints()));
        userScore.setLevel(calculateLevel(userScore.getTotalPoints()));
        userScore.setCurrentBadge(calculateBadge(userScore.getLevel()));
        userScore.setLastUpdated(LocalDateTime.now());
        
        UserScore savedUserScore = userScoreRepository.save(userScore);
        return convertToDTO(savedUserScore);
    }

    public UserScoreDTO addPointsForGoodDecision(Long userId) {
        return updateUserScore(userId, 10);
    }

    public UserScoreDTO addPointsForSimulation(Long userId) {
        return updateUserScore(userId, 5);
    }

    public UserScoreDTO subtractPointsForBetting(Long userId) {
        return updateUserScore(userId, -3);
    }

    private UserScore createInitialUserScore(Long userId) {
        UserScore userScore = new UserScore();
        userScore.setUserId(userId);
        userScore.setFinancialIntelligenceScore(100);
        userScore.setTotalPoints(0);
        userScore.setLevel(1);
        userScore.setCurrentBadge("INICIANTE");
        userScore.setLastUpdated(LocalDateTime.now());
        return userScoreRepository.save(userScore);
    }

    private Integer calculateFinancialIntelligenceScore(Integer totalPoints) {
        // Score base de 100, aumenta com pontos positivos
        return Math.max(0, Math.min(1000, 100 + totalPoints));
    }

    private Integer calculateLevel(Integer totalPoints) {
        // Cada 100 pontos = 1 nível
        return Math.max(1, (totalPoints / 100) + 1);
    }

    private String calculateBadge(Integer level) {
        if (level >= 10) return "MESTRE FINANCEIRO";
        if (level >= 7) return "ESPECIALISTA";
        if (level >= 5) return "AVANÇADO";
        if (level >= 3) return "INTERMEDIÁRIO";
        return "INICIANTE";
    }

    private UserScoreDTO convertToDTO(UserScore userScore) {
        UserScoreDTO dto = new UserScoreDTO();
        dto.setId(userScore.getId());
        dto.setUserId(userScore.getUserId());
        dto.setFinancialIntelligenceScore(userScore.getFinancialIntelligenceScore());
        dto.setTotalPoints(userScore.getTotalPoints());
        dto.setLevel(userScore.getLevel());
        dto.setCurrentBadge(userScore.getCurrentBadge());
        dto.setLastUpdated(userScore.getLastUpdated());
        return dto;
    }
}

