package com.bytes.train.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.AgentCategoryService;

@SpringBootTest(classes = CustomerTicketingSupportApplication.class)
public class CustomerServiceImplTests {
 
	@MockBean
	AgentRepository agentRepository;

	@Autowired
	AgentCategoryService agentCategoryServiceImpl;
	
	
	@Mock
    private AgentCategoryService agentService;

    @MockBean
    private TicketRepository ticketRepository ;
    
//
//	Agent agentDetails() {
//		Agent agent = new Agent();
//		agent.setAgentName("a");
//		agent.setAgentPassword("a");
//		return agent;
//	}

//	@Test
//
//	void TestcheckAccessCorrect() throws Exception {
//		String name="n";
//		String password="n";
//	   Mockito.when(agentRepository.findByAgentName(name)).thenReturn(new Agent(0, "n",null, "n", null,null, null));
//	  System.out.println();
//	  assertEquals(password, agentCategoryServiceImpl.checkAccess(name, password).getAgentPassword());
//	}

//	@Test
//	void TestcheckAccessNotCorrectPassword() throws Exception {

//		CustomerServiceImplTests customer = new CustomerServiceImplTests();
//		String name = "n";
//		String password = "k";
//		Mockito.when(agentRepository.findByAgentName(name)).thenReturn(new Agent(0, "n", null, "k", null, null, null));
//		System.out.println();
//		assertEquals(password, agentCategoryServiceImpl.checkAccess(name, password).getAgentPassword());
//	}
//
//	@Test
//	void TestcheckAccessNoData() throws Exception {
//			String name="k";
//		String password="k";
//	   Mockito.when(agentRepository.findByAgentName(name)).thenReturn(null);
//	  System.out.println();
//	  assertEquals(password, agentCategoryServiceImpl.checkAccess(name, password).getAgentPassword());
//	}
	

    
//    @MockBean
//	AgentRepository agentRepository;
//
//
    @Test
	void TestCheckAgentParticularList() {
		 int agentId = 123;
//
//	        // Sample agent and category data for testing
	        Agent agent = new Agent();
	        agent.setAgentID(agentId);
	        agent.setAddress(null);
	        agent.setAgentName(null);
//	        agent
	       

	        Category category1 = new Category();
	        category1.setCategoryId(1);
	        category1.setCategory_name("Hardware");
	        
	        Category category2 = new Category();
	        category2.setCategoryId(2); 
	        category2.setCategory_name("Software");
	        
	        agent.setCategory(Arrays.asList(category1,category2));

	        // Sample ticket data for testing
	        
	        Ticket ticket1 = new Ticket();
	        ticket1.setTicketId(101);
	        ticket1.setCategoryId(category1);
	        ticket1.setStatus("Open");
	        ticket1.setAgentId(agent);
	        ticket1.setCategoryName("Hardware");

	        Ticket ticket2 = new Ticket();
	        ticket2.setTicketId(102);
	        ticket2.setCategoryId(category2);
	        ticket2.setStatus("Open");
	        ticket2.setAgentId(agent); 
	        ticket2.setCategoryName("Software");
	        
	        
	        
	        
//	        
	        Mockito.when(agentRepository.findById(agentId)).thenReturn(Optional.of(agent));
	        System.out.println(Optional.of(agent));

	        Mockito.when(ticketRepository.findAllByCategoryIdInAndStatusIn(anyList(),any())).thenReturn(List.of(ticket1, ticket2));
	        System.out.println(List.of(ticket1, ticket2));

	        
	        List<Ticket> agentTickets = agentService.getParticularCategoryList(agentId);

	        System.out.println(agentTickets.size());
	        
	        // Assertions
//	        assertNotNull(agentTickets);
	        assertEquals(2,agentTickets.size());
	        assertEquals(2,agentTickets.size());
	        assertEquals(ticket1, agentTickets.get(0));
	        assertEquals(ticket2, agentTickets.get(1));
	        
	        
	        
	        
	        
//	        int agentId = 123;
//
//	        // Sample agent and category data for testing
//	        Agent agent = new Agent();
//	        agent.setAgentID(agentId);
//
//	        Category category1 = new Category();
//	        category1.setCategoryId(1);
//	        category1.setCategory_name("Hardware");
//
//	        Category category2 = new Category();
//	        category2.setCategoryId(2);
//	        category2.setCategory_name("Software");
//
//	        agent.setCategory(Arrays.asList(category1, category2));
//
//	        // Sample ticket data for testing
//	        Ticket ticket1 = new Ticket();
//	        ticket1.setTicketId(101);
//	        ticket1.setCategoryId(category1);
//	        ticket1.setStatus("Open");
//	        ticket1.setAgentId(agent);
//	        ticket1.setCategoryName("Hardware");
//
//	        Ticket ticket2 = new Ticket();
//	        ticket2.setTicketId(102);
//	        ticket2.setCategoryId(category2);
//	        ticket2.setStatus("Open");
//	        ticket2.setAgentId(agent);
//	        ticket2.setCategoryName("Software");
//
//	        // Mock agentRepository and ticketRepository
////	        AgentRepository agentRepository = mock(AgentRepository.class);
////	        TicketRepository ticketRepository = mock(TicketRepository.class);
//
//	        // Set up behavior for the mock repositories
//	        when(agentRepository.findById(agentId)).thenReturn(Optional.of(agent));
//	        when(ticketRepository.findAllByCategoryIdInAndStatusIn(anyList(), any())).thenReturn(List.of(ticket1, ticket2));
//
//	        // Create the agentService with mocked repositories
////	        AgentService agentService = new AgentService(agentRepository, ticketRepository);
//
//	        // Call the method under test
//	        List<Ticket> agentTickets = agentService.getParticularCategoryList(agentId);
//
//	        // Verify interactions with mock repositories
//	        verify(agentRepository, times(1)).findById(agentId);
//	        verify(ticketRepository, times(1)).findAllByCategoryIdInAndStatusIn(anyList(), any());
//
//	        // Assertions
//	        assertEquals(2, agentTickets.size());
//	        // Additional assertions if needed
//	        // assertEquals(ticket1, agentTickets.get(0));
//	        // assertEquals(ticket2, agentTickets.get(1));
	}
  

}
