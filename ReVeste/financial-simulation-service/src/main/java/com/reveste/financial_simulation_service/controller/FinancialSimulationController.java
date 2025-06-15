package com.reveste.financial_simulation_service.controller;

import com.reveste.financial_simulation_service.dto.FinancialSimulationDTO;
import com.reveste.financial_simulation_service.model.FinancialSimulation;
import com.reveste.financial_simulation_service.service.FinancialSimulationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulations")
@Tag(name = "Financial Simulation", description = "APIs para simulações financeiras")
@CrossOrigin(origins = "*")
public class FinancialSimulationController {

    @Autowired
    private FinancialSimulationService simulationService;

    @GetMapping
    @Operation(summary = "Listar todas as simulações", description = "Retorna uma lista de todas as simulações financeiras")
    public ResponseEntity<List<FinancialSimulationDTO>> getAllSimulations() {
        List<FinancialSimulationDTO> simulations = simulationService.getAllSimulations();
        return ResponseEntity.ok(simulations);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Buscar simulações por usuário", description = "Retorna todas as simulações de um usuário específico")
    public ResponseEntity<List<FinancialSimulationDTO>> getSimulationsByUserId(@PathVariable Long userId) {
        List<FinancialSimulationDTO> simulations = simulationService.getSimulationsByUserId(userId);
        return ResponseEntity.ok(simulations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar simulação por ID", description = "Retorna uma simulação específica pelo ID")
    public ResponseEntity<FinancialSimulationDTO> getSimulationById(@PathVariable Long id) {
        FinancialSimulationDTO simulation = simulationService.getSimulationById(id);
        if (simulation != null) {
            return ResponseEntity.ok(simulation);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Criar nova simulação", description = "Cria uma nova simulação financeira")
    public ResponseEntity<FinancialSimulationDTO> createSimulation(@RequestBody FinancialSimulation simulation) {
        FinancialSimulationDTO createdSimulation = simulationService.createSimulation(simulation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSimulation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar simulação", description = "Atualiza os dados de uma simulação existente")
    public ResponseEntity<FinancialSimulationDTO> updateSimulation(@PathVariable Long id, @RequestBody FinancialSimulation simulationDetails) {
        FinancialSimulationDTO updatedSimulation = simulationService.updateSimulation(id, simulationDetails);
        if (updatedSimulation != null) {
            return ResponseEntity.ok(updatedSimulation);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar simulação", description = "Remove uma simulação do sistema")
    public ResponseEntity<Void> deleteSimulation(@PathVariable Long id) {
        boolean deleted = simulationService.deleteSimulation(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

