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
	
	
	@Mock
    private AgentCategoryService agentService;

    @MockBean
    private TicketRepository ticketRepository ;
    
    
//	@Test
//
//	void TestcheckAccessCorrect() throws Exception {
//		String name="n";
//		String password="n";
//	   Mockito.when(agentRepository.findByAgentName(name)).thenReturn(new Agent(0, "n",null, "n", null,null, null));
//	  System.out.println();
//	  assertEquals(password, agentCategoryServiceImpl.checkAccess(name, password).getAgentPassword());
//	}
//    
	
	 @Test
	
	    public void testGetFullInfo() {
	        // Sample agent data for testing
	        Agent agent1 = new Agent(1, "John Doe", null, null, null, null, null);
	        Agent agent2 = new Agent(2, "Jane Smith", null, null, null, null, null);
	        List<Agent> sampleAgents = Arrays.asList(agent1, agent2);

	        // Mock the behavior of the agentRepository.findAll() method
	        when(agentRepository.findAll()).thenReturn(sampleAgents);

	        // Call the method under test
	        List<Agent> agents = agentService.getFullInfo();

	        // Verify that agentRepository.findAll() was called once
	        verify(agentRepository, times(1)).findAll();

	        // Assertions
	        assertNotNull(agents);
	        assertEquals(2, agents.size());
	        assertEquals("John Doe", agents.get(0).getAgentName());
	        assertEquals("Jane Smith", agents.get(1).getAgentName());
	        // Additional assertions if needed for other properties of the agents
	    }

}
