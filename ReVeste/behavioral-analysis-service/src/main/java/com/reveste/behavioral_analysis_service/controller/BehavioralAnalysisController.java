package com.reveste.behavioral_analysis_service.controller;

import com.reveste.behavioral_analysis_service.dto.BehavioralAlertDTO;
import com.reveste.behavioral_analysis_service.model.BehavioralAlert;
import com.reveste.behavioral_analysis_service.service.BehavioralAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/behavioral-alerts")
@Tag(name = "Behavioral Analysis", description = "APIs para análise comportamental e alertas")
@CrossOrigin(origins = "*")
public class BehavioralAnalysisController {

    @Autowired
    private BehavioralAnalysisService behavioralService;

    @GetMapping
    @Operation(summary = "Listar todos os alertas", description = "Retorna uma lista de todos os alertas comportamentais")
    public ResponseEntity<List<BehavioralAlertDTO>> getAllAlerts() {
        List<BehavioralAlertDTO> alerts = behavioralService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Buscar alertas por usuário", description = "Retorna todos os alertas de um usuário específico")
    public ResponseEntity<List<BehavioralAlertDTO>> getAlertsByUserId(@PathVariable Long userId) {
        List<BehavioralAlertDTO> alerts = behavioralService.getAlertsByUserId(userId);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/user/{userId}/active")
    @Operation(summary = "Buscar alertas ativos por usuário", description = "Retorna todos os alertas ativos de um usuário específico")
    public ResponseEntity<List<BehavioralAlertDTO>> getActiveAlertsByUserId(@PathVariable Long userId) {
        List<BehavioralAlertDTO> alerts = behavioralService.getActiveAlertsByUserId(userId);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar alerta por ID", description = "Retorna um alerta específico pelo ID")
    public ResponseEntity<BehavioralAlertDTO> getAlertById(@PathVariable Long id) {
        BehavioralAlertDTO alert = behavioralService.getAlertById(id);
        if (alert != null) {
            return ResponseEntity.ok(alert);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Criar novo alerta", description = "Cria um novo alerta comportamental")
    public ResponseEntity<BehavioralAlertDTO> createAlert(@RequestBody BehavioralAlert alert) {
        BehavioralAlertDTO createdAlert = behavioralService.createAlert(alert);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlert);
    }

    @PostMapping("/analyze/{userId}")
    @Operation(summary = "Analisar comportamento e criar alerta", description = "Analisa o comportamento do usuário e cria alerta se necessário")
    public ResponseEntity<BehavioralAlertDTO> analyzeAndCreateAlert(@PathVariable Long userId, @RequestParam BigDecimal betAmount) {
        BehavioralAlertDTO alert = behavioralService.analyzeAndCreateAlert(userId, betAmount);
        return ResponseEntity.status(HttpStatus.CREATED).body(alert);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar alerta", description = "Atualiza os dados de um alerta existente")
    public ResponseEntity<BehavioralAlertDTO> updateAlert(@PathVariable Long id, @RequestBody BehavioralAlert alertDetails) {
        BehavioralAlertDTO updatedAlert = behavioralService.updateAlert(id, alertDetails);
        if (updatedAlert != null) {
            return ResponseEntity.ok(updatedAlert);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar alerta", description = "Remove um alerta do sistema")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        boolean deleted = behavioralService.deleteAlert(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

