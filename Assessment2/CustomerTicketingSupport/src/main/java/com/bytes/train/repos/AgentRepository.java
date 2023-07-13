package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
	
	Agent findByagentName(String agentName);
	
	
}
