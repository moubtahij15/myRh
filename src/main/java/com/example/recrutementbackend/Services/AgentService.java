package com.example.recrutementbackend.Services;

import com.example.recrutementbackend.Entities.Agent;
import com.example.recrutementbackend.Repositories.AgentRepository;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    Agent loadUserByUsername(String email) {
        return agentRepository.findByEmail(email);
    }
}
