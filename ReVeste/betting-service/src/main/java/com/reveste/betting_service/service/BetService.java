package com.reveste.betting_service.service;

import com.reveste.betting_service.dto.BetDTO;
import com.reveste.betting_service.model.Bet;
import com.reveste.betting_service.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;

    public List<BetDTO> getAllBets() {
        return betRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BetDTO> getBetsByUserId(Long userId) {
        return betRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BetDTO getBetById(Long id) {
        Bet bet = betRepository.findById(id).orElse(null);
        return bet != null ? convertToDTO(bet) : null;
    }

    public BetDTO createBet(Bet bet) {
        bet.setBetDate(LocalDateTime.now());
        Bet savedBet = betRepository.save(bet);
        return convertToDTO(savedBet);
    }

    public BetDTO updateBet(Long id, Bet betDetails) {
        Bet bet = betRepository.findById(id).orElse(null);
        if (bet != null) {
            bet.setAmount(betDetails.getAmount());
            bet.setDescription(betDetails.getDescription());
            Bet updatedBet = betRepository.save(bet);
            return convertToDTO(updatedBet);
        }
        return null;
    }

    public boolean deleteBet(Long id) {
        if (betRepository.existsById(id)) {
            betRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private BetDTO convertToDTO(Bet bet) {
        BetDTO betDTO = new BetDTO();
        betDTO.setId(bet.getId());
        betDTO.setUserId(bet.getUserId());
        betDTO.setAmount(bet.getAmount());
        betDTO.setDescription(bet.getDescription());
        betDTO.setBetDate(bet.getBetDate());
        return betDTO;
    }
}

