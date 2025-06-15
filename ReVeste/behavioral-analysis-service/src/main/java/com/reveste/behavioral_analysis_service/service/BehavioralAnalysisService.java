package com.reveste.behavioral_analysis_service.service;

import com.reveste.behavioral_analysis_service.dto.BehavioralAlertDTO;
import com.reveste.behavioral_analysis_service.model.BehavioralAlert;
import com.reveste.behavioral_analysis_service.repository.BehavioralAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BehavioralAnalysisService {

    @Autowired
    private BehavioralAlertRepository alertRepository;

    public List<BehavioralAlertDTO> getAllAlerts() {
        return alertRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BehavioralAlertDTO> getAlertsByUserId(Long userId) {
        return alertRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BehavioralAlertDTO> getActiveAlertsByUserId(Long userId) {
        return alertRepository.findByUserIdAndIsActive(userId, true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BehavioralAlertDTO getAlertById(Long id) {
        BehavioralAlert alert = alertRepository.findById(id).orElse(null);
        return alert != null ? convertToDTO(alert) : null;
    }

    public BehavioralAlertDTO createAlert(BehavioralAlert alert) {
        alert.setAlertDate(LocalDateTime.now());
        alert.setIsActive(true);
        BehavioralAlert savedAlert = alertRepository.save(alert);
        return convertToDTO(savedAlert);
    }

    public BehavioralAlertDTO analyzeAndCreateAlert(Long userId, BigDecimal betAmount) {
        // Análise comportamental simples
        String alertType = "FREQUENT_BETTING";
        String description = "Padrão de apostas frequentes detectado";
        String severity = "MEDIUM";
        
        if (betAmount.compareTo(new BigDecimal("500")) > 0) {
            alertType = "HIGH_VALUE_BET";
            description = "Aposta de alto valor detectada";
            severity = "HIGH";
        }
        
        BehavioralAlert alert = new BehavioralAlert();
        alert.setUserId(userId);
        alert.setAlertType(alertType);
        alert.setDescription(description);
        alert.setSeverity(severity);
        alert.setTriggerAmount(betAmount);
        alert.setAlertDate(LocalDateTime.now());
        alert.setIsActive(true);
        
        BehavioralAlert savedAlert = alertRepository.save(alert);
        return convertToDTO(savedAlert);
    }

    public BehavioralAlertDTO updateAlert(Long id, BehavioralAlert alertDetails) {
        BehavioralAlert alert = alertRepository.findById(id).orElse(null);
        if (alert != null) {
            alert.setAlertType(alertDetails.getAlertType());
            alert.setDescription(alertDetails.getDescription());
            alert.setSeverity(alertDetails.getSeverity());
            alert.setIsActive(alertDetails.getIsActive());
            BehavioralAlert updatedAlert = alertRepository.save(alert);
            return convertToDTO(updatedAlert);
        }
        return null;
    }

    public boolean deleteAlert(Long id) {
        if (alertRepository.existsById(id)) {
            alertRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private BehavioralAlertDTO convertToDTO(BehavioralAlert alert) {
        BehavioralAlertDTO dto = new BehavioralAlertDTO();
        dto.setId(alert.getId());
        dto.setUserId(alert.getUserId());
        dto.setAlertType(alert.getAlertType());
        dto.setDescription(alert.getDescription());
        dto.setSeverity(alert.getSeverity());
        dto.setTriggerAmount(alert.getTriggerAmount());
        dto.setAlertDate(alert.getAlertDate());
        dto.setIsActive(alert.getIsActive());
        return dto;
    }
}

