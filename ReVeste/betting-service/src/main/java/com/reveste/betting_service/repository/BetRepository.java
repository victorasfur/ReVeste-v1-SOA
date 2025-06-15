package com.reveste.betting_service.repository;

import com.reveste.betting_service.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByUserId(Long userId);
}

