package com.reveste.financial_simulation_service.service;

import com.reveste.financial_simulation_service.dto.FinancialSimulationDTO;
import com.reveste.financial_simulation_service.model.FinancialSimulation;
import com.reveste.financial_simulation_service.repository.FinancialSimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialSimulationService {

    @Autowired
    private FinancialSimulationRepository simulationRepository;

    public List<FinancialSimulationDTO> getAllSimulations() {
        return simulationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FinancialSimulationDTO> getSimulationsByUserId(Long userId) {
        return simulationRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FinancialSimulationDTO getSimulationById(Long id) {
        FinancialSimulation simulation = simulationRepository.findById(id).orElse(null);
        return simulation != null ? convertToDTO(simulation) : null;
    }

    public FinancialSimulationDTO createSimulation(FinancialSimulation simulation) {
        simulation.setSimulationDate(LocalDateTime.now());
        
        // Calcular valor projetado usando juros compostos
        BigDecimal projectedValue = calculateCompoundInterest(
            simulation.getMonthlyAmount(),
            simulation.getAnnualRate(),
            simulation.getYears()
        );
        simulation.setProjectedValue(projectedValue);
        
        FinancialSimulation savedSimulation = simulationRepository.save(simulation);
        return convertToDTO(savedSimulation);
    }

    public FinancialSimulationDTO updateSimulation(Long id, FinancialSimulation simulationDetails) {
        FinancialSimulation simulation = simulationRepository.findById(id).orElse(null);
        if (simulation != null) {
            simulation.setMonthlyAmount(simulationDetails.getMonthlyAmount());
            simulation.setInvestmentType(simulationDetails.getInvestmentType());
            simulation.setAnnualRate(simulationDetails.getAnnualRate());
            simulation.setYears(simulationDetails.getYears());
            
            // Recalcular valor projetado
            BigDecimal projectedValue = calculateCompoundInterest(
                simulation.getMonthlyAmount(),
                simulation.getAnnualRate(),
                simulation.getYears()
            );
            simulation.setProjectedValue(projectedValue);
            
            FinancialSimulation updatedSimulation = simulationRepository.save(simulation);
            return convertToDTO(updatedSimulation);
        }
        return null;
    }

    public boolean deleteSimulation(Long id) {
        if (simulationRepository.existsById(id)) {
            simulationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private BigDecimal calculateCompoundInterest(BigDecimal monthlyAmount, Double annualRate, Integer years) {
        double monthlyRate = annualRate / 12 / 100;
        int totalMonths = years * 12;
        
        double futureValue = 0;
        for (int i = 0; i < totalMonths; i++) {
            futureValue = (futureValue + monthlyAmount.doubleValue()) * (1 + monthlyRate);
        }
        
        return BigDecimal.valueOf(futureValue).setScale(2, RoundingMode.HALF_UP);
    }

    private FinancialSimulationDTO convertToDTO(FinancialSimulation simulation) {
        FinancialSimulationDTO dto = new FinancialSimulationDTO();
        dto.setId(simulation.getId());
        dto.setUserId(simulation.getUserId());
        dto.setMonthlyAmount(simulation.getMonthlyAmount());
        dto.setInvestmentType(simulation.getInvestmentType());
        dto.setAnnualRate(simulation.getAnnualRate());
        dto.setYears(simulation.getYears());
        dto.setProjectedValue(simulation.getProjectedValue());
        dto.setSimulationDate(simulation.getSimulationDate());
        return dto;
    }
}

