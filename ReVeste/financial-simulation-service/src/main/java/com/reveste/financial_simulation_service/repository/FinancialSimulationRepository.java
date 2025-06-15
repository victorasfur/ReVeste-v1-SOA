package com.reveste.financial_simulation_service.repository;

import com.reveste.financial_simulation_service.model.FinancialSimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialSimulationRepository extends JpaRepository<FinancialSimulation, Long> {
    List<FinancialSimulation> findByUserId(Long userId);
}

