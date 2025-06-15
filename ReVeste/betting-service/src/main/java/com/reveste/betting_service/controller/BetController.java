package com.reveste.betting_service.controller;

import com.reveste.betting_service.dto.BetDTO;
import com.reveste.betting_service.model.Bet;
import com.reveste.betting_service.service.BetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bets")
@Tag(name = "Bet Management", description = "APIs para gerenciamento de apostas")
@CrossOrigin(origins = "*")
public class BetController {

    @Autowired
    private BetService betService;

    @GetMapping
    @Operation(summary = "Listar todas as apostas", description = "Retorna uma lista de todas as apostas registradas")
    public ResponseEntity<List<BetDTO>> getAllBets() {
        List<BetDTO> bets = betService.getAllBets();
        return ResponseEntity.ok(bets);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Buscar apostas por usuário", description = "Retorna todas as apostas de um usuário específico")
    public ResponseEntity<List<BetDTO>> getBetsByUserId(@PathVariable Long userId) {
        List<BetDTO> bets = betService.getBetsByUserId(userId);
        return ResponseEntity.ok(bets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aposta por ID", description = "Retorna uma aposta específica pelo ID")
    public ResponseEntity<BetDTO> getBetById(@PathVariable Long id) {
        BetDTO bet = betService.getBetById(id);
        if (bet != null) {
            return ResponseEntity.ok(bet);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Registrar nova aposta", description = "Registra uma nova aposta no sistema")
    public ResponseEntity<BetDTO> createBet(@RequestBody Bet bet) {
        BetDTO createdBet = betService.createBet(bet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBet);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aposta", description = "Atualiza os dados de uma aposta existente")
    public ResponseEntity<BetDTO> updateBet(@PathVariable Long id, @RequestBody Bet betDetails) {
        BetDTO updatedBet = betService.updateBet(id, betDetails);
        if (updatedBet != null) {
            return ResponseEntity.ok(updatedBet);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aposta", description = "Remove uma aposta do sistema")
    public ResponseEntity<Void> deleteBet(@PathVariable Long id) {
        boolean deleted = betService.deleteBet(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

