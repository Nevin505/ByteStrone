package com.bytes.train.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.Agent;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.AgentCategoryService;

@SpringBootTest(classes = CustomerTicketingSupportApplication.class)
public class Tests {
	@MockBean
	AgentRepository agentRepository;

	@Autowired
	AgentCategoryService agentCategoryServiceImpl;

	@MockBean
	private TicketRepository ticketRepository;

	@Test
	public void testGetFullInfo() {
		// Sample agent data for testing
		Agent agent1 = new Agent(1, "John Doe", null, null, null, null, null, null);
		Agent agent2 = new Agent(2, "Jane Smith", null, null, null, null, null, null);
		List<Agent> sampleAgents = Arrays.asList(agent1, agent2);

		Mockito.when(agentRepository.findAll()).thenReturn(sampleAgents);

		agentRepository.save(agent1);
		agentRepository.save(agent2);

		List<Agent> agents = agentRepository.findAll();

		assertEquals(2, agents.size());

	}

}
