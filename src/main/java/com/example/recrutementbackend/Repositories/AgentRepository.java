package com.example.recrutementbackend.Repositories;

import com.example.recrutementbackend.Entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    public Agent findByEmail(String email);
}
