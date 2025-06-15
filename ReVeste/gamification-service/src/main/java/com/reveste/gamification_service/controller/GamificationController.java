package com.reveste.gamification_service.controller;

import com.reveste.gamification_service.dto.UserScoreDTO;
import com.reveste.gamification_service.service.GamificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gamification")
@Tag(name = "Gamification", description = "APIs para sistema de gamificação e pontuação")
@CrossOrigin(origins = "*")
public class GamificationController {

    @Autowired
    private GamificationService gamificationService;

    @GetMapping("/scores")
    @Operation(summary = "Listar todas as pontuações", description = "Retorna uma lista de todas as pontuações dos usuários")
    public ResponseEntity<List<UserScoreDTO>> getAllUserScores() {
        List<UserScoreDTO> scores = gamificationService.getAllUserScores();
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/score/user/{userId}")
    @Operation(summary = "Buscar pontuação por usuário", description = "Retorna a pontuação de um usuário específico")
    public ResponseEntity<UserScoreDTO> getUserScoreByUserId(@PathVariable Long userId) {
        UserScoreDTO score = gamificationService.getUserScoreByUserId(userId);
        return ResponseEntity.ok(score);
    }

    @PostMapping("/score/user/{userId}/good-decision")
    @Operation(summary = "Adicionar pontos por boa decisão", description = "Adiciona pontos ao usuário por tomar uma boa decisão financeira")
    public ResponseEntity<UserScoreDTO> addPointsForGoodDecision(@PathVariable Long userId) {
        UserScoreDTO updatedScore = gamificationService.addPointsForGoodDecision(userId);
        return ResponseEntity.ok(updatedScore);
    }

    @PostMapping("/score/user/{userId}/simulation")
    @Operation(summary = "Adicionar pontos por simulação", description = "Adiciona pontos ao usuário por realizar uma simulação financeira")
    public ResponseEntity<UserScoreDTO> addPointsForSimulation(@PathVariable Long userId) {
        UserScoreDTO updatedScore = gamificationService.addPointsForSimulation(userId);
        return ResponseEntity.ok(updatedScore);
    }

    @PostMapping("/score/user/{userId}/betting")
    @Operation(summary = "Subtrair pontos por aposta", description = "Subtrai pontos do usuário por realizar uma aposta")
    public ResponseEntity<UserScoreDTO> subtractPointsForBetting(@PathVariable Long userId) {
        UserScoreDTO updatedScore = gamificationService.subtractPointsForBetting(userId);
        return ResponseEntity.ok(updatedScore);
    }

    @PostMapping("/score/user/{userId}/update")
    @Operation(summary = "Atualizar pontuação", description = "Atualiza a pontuação do usuário com pontos específicos")
    public ResponseEntity<UserScoreDTO> updateUserScore(@PathVariable Long userId, @RequestParam Integer points) {
        UserScoreDTO updatedScore = gamificationService.updateUserScore(userId, points);
        return ResponseEntity.ok(updatedScore);
    }
}

