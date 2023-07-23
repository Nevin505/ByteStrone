package com.bytes.train.serviceImpl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.entities.Agent;
import com.bytes.train.repos.AgentRepository;

public class Tickets {
	
	
	@MockBean
	AgentRepository agentRepository;
	
	 @Test
	 public void testGetFullInfo() {
        // Sample agent data for testing
        Agent agent1 = new Agent(1, "John Doe", null, null, null, null, null);
        Agent agent2 = new Agent(2, "Jane Smith", null, null, null, null, null);
        List<Agent> sampleAgents = Arrays.asList(agent1, agent2);
        
        
        agentRepository.save(agent1);
        agentRepository.save(agent2);
        
        List<Agent> agents= agentRepository.findAll();
        
        assertEquals(2, agents.size());
        
	 }

}
