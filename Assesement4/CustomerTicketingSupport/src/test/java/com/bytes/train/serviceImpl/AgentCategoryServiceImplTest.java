package com.bytes.train.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.AgentCategoryService;
import com.bytes.train.serviceimpl.AgentCategoryServiceImpl;

@SpringBootTest(classes = CustomerTicketingSupportApplication.class)
public class AgentCategoryServiceImplTest {
	@MockBean
	private AgentRepository agentRepository;

	@Autowired
	private AgentCategoryServiceImpl agentCategoryServiceImpl;

	@Autowired
	private AgentCategoryService agentService;

	@MockBean
	private TicketRepository ticketRepository;

	 Agent getDetails() {
		int agentId = 123;
		Agent agent = new Agent();
		agent.setId(agentId);
		agent.setAddress(null);
		agent.setUserName(null);
		return agent;
	}

	static List<Category> getCategoryDetails() {
		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategory_name("Camera");
		Category category2 = new Category();
		category2.setCategoryId(2);
		category2.setCategory_name("Printer");
		return Arrays.asList(category1, category2);
	}

	@Test
	public void testGetSpecificTicketId() throws Exception {

		int ticketId = 1;
		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(ticketId);
		Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(mockTicket));

		Ticket resultTicket = agentService.getSpecificTicketId(ticketId);

		assertNotNull(resultTicket);
		assertEquals(ticketId, resultTicket.getTicketId());
	}
	
	 @Test
	 public void whenExceptionThrown_testNotGetSpecificTicketId() {
		  
			int ticketId = 1;
			
			Ticket mockTicket = new Ticket();
			mockTicket.setTicketId(ticketId);
			Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(mockTicket));
			int getTicket=2;
	     Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
	    	 agentService.getSpecificTicketId(getTicket);
	     });

	     String expectedMessage = "There Exist No Ticket";
	     String actualMessage = exception.getMessage();

	     assertTrue(actualMessage.contains(expectedMessage));
	 }
	@Test
	void testGetParticularCategoryList() throws Exception {
		int agentId = 123;

		Agent mockAgent =getDetails();

		List<Category> categories = AgentCategoryServiceImplTest.getCategoryDetails();

		mockAgent.setCategory(categories); // You can customize this as needed

		System.out.println(mockAgent);

		Mockito.when(agentRepository.findById(agentId)).thenReturn(Optional.of(mockAgent));

		Category category1 = categories.get(0);

		Category category2 = categories.get(1);

		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(101);
		ticket1.setCategoryId(category1);
		ticket1.setStatus("Open");
		ticket1.setAgentId(mockAgent);
		ticket1.setCategoryName("Hardware");

		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(102);
		ticket2.setCategoryId(category2);
		ticket2.setStatus("Open");
		ticket2.setAgentId(mockAgent);
		ticket2.setCategoryName("Software");

		List<Category> agentCategories = Arrays.asList(category1, category2);

		String[] status = { "Open", "Closed" };

		List<Ticket> mockTickets = Arrays.asList(ticket1, ticket2);
		Mockito.when(ticketRepository.findAllByCategoryIdInAndStatusIn(agentCategories, status))
				.thenReturn(mockTickets);


		List<Ticket> result = agentService.getParticularCategoryList(agentId);


		verify(agentRepository, times(1)).findById(agentId);
		verify(ticketRepository, times(1)).findAllByCategoryIdInAndStatusIn(any(), any());

		System.out.println(result.size());
		assertEquals(2, result.size());

		assertEquals(mockTickets.size(), result.size());
	}

	@Test
	void testGetfilteredTickets() throws Exception {
		int agentId = 1;
		String status = "Open"; 


		Agent mockAgent = new Agent();
		mockAgent.setId(agentId);
		mockAgent.setCategory(Arrays.asList(new Category()));

		Mockito.when(agentRepository.findById(agentId)).thenReturn(Optional.of(mockAgent));


		List<Ticket> mockTickets = Arrays.asList(new Ticket(), new Ticket());
		Mockito.when(ticketRepository.findAllByCategoryIdInAndStatus(any(), eq(status))).thenReturn(mockTickets);


		List<Ticket> result = agentService.getfilteredTickets(agentId, status);


		verify(agentRepository, times(1)).findById(agentId);
		verify(ticketRepository, times(1)).findAllByCategoryIdInAndStatus(any(), eq(status));

		
		assertEquals(mockTickets.size(), result.size());
	}

	@Test
	public void testAssignToAgents() throws Exception {
		// Arrange
		int ticketId = 1;
		int agentId = 1;

		List<Category> categories = AgentCategoryServiceImplTest.getCategoryDetails();

		Category category1 = categories.get(0);

		Category category2 = categories.get(1);

		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(ticketId);
		mockTicket.setAgentId(null); 
		mockTicket.setCategoryId(category1);
		Agent mockAgent = new Agent();
		mockAgent.setId(agentId);
		mockAgent.setCategory(categories); 

		Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(mockTicket));
		Mockito.when(agentRepository.findById(agentId)).thenReturn(Optional.of(mockAgent));


		String result = agentService.assignToAgents(ticketId, agentId);


		assertEquals("The Ticket Is Being Assigined", result);
		assertEquals(mockAgent, mockTicket.getAgentId());
		assertEquals("Assigned", mockTicket.getStatus());
		assertEquals(1, mockAgent.getAgentCount());
		verify(ticketRepository, times(1)).save(mockTicket);
		verify(agentRepository, times(1)).save(mockAgent);
	}

	@Test
	public void testGetAssignedTickets() throws Exception {
		Agent agent = new Agent();
		agent.setId(1);
		Mockito.when(agentRepository.findById(eq(1))).thenReturn(Optional.of(agent));


		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		List<Ticket> assignedTickets = new ArrayList<>();
		assignedTickets.add(ticket1);
		assignedTickets.add(ticket2);
		when(ticketRepository.findByAgentIdAndStatus(eq(agent), eq("Assigned"))).thenReturn(assignedTickets);


		List<Ticket> result = agentService.getAssignedTickets(1);


		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(ticket1, result.get(0));
		Assertions.assertEquals(ticket2, result.get(1));
	}

	@Test
	public void testCloseTickets() throws Exception {

		Agent agent = new Agent();
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setStatus("Assigned");
		ticket.setAgentId(agent);

		agent.setId(1);
		agent.setAgentCount(1);


		Mockito.when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
		Mockito.when(agentRepository.findById(1)).thenReturn(Optional.of(agent));


		Ticket result = agentService.closeTickets(1, 1);


		assertNotNull(result);
		assertEquals("Closed", result.getStatus());
		assertEquals(0, agent.getAgentCount());


		verify(ticketRepository, times(1)).save(ticket);
		verify(agentRepository, times(1)).save(agent);
	}

	@Test
	public void testGetAgents() throws Exception {

		Agent agent = new Agent();
		agent.setId(1);
		Category category = new Category();
		category.setCategoryId(1);
		agent.setCategory(Arrays.asList(category));


		when(agentRepository.findById(1)).thenReturn(Optional.of(agent));
		when(agentRepository.findByCategoryIn(Arrays.asList(category))).thenReturn(Arrays.asList(agent));


		List<Agent> result = agentService.getAgents(1);


		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(agent.getId(), result.get(0).getId());
	}

	@Test
	public void testGetSearchWithValidAgentAndStatus() throws Exception {

		List<Category> categorie = AgentCategoryServiceImplTest.getCategoryDetails();
		Agent agent = new Agent();
		agent.setId(1);
		agent.setCategory(categorie);

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setStatus("Open");
		searchCriteria.setSubject("Ticket Subject");

		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setSubject("Ticket Subject");
		ticket.setStatus("Open");
		tickets.add(ticket);


		when(agentRepository.findById(1)).thenReturn(Optional.of(agent));
		when(ticketRepository.findAllByCategoryIdInAndStatus(categorie, "Open")).thenReturn(tickets);


		List<Ticket> result = agentService.getSearch(1, searchCriteria);


		assertEquals(1, result.size());
		assertEquals(ticket, result.get(0));
	}


//	@Test
//	public void testIsNumericWithNumericString() {
//		assertTrue(agentCategoryServiceImpl.isNumeric("123"));
//	}
//
//	@Test
//	public void testIsNumericWithNonNumericString() {
//		assertFalse(agentCategoryServiceImpl.isNumeric("abc"));
//	}
//	
	@Test
	public void testGetSearchWithValidAgentAndStatusOpenClosed() throws Exception {
		List<Category> categorie = AgentCategoryServiceImplTest.getCategoryDetails();
		Agent agent = new Agent();
		agent.setId(1);
		agent.setCategory(categorie);
		
		
		Category category1=categorie.get(0);

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setStatus(" ");
		searchCriteria.setSubject("Ticket Subject");

		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setCategoryId(category1);
		ticket.setSubject("Ticket Subject");
		ticket.setStatus("Open");
		tickets.add(ticket);
		
		
		String[] status = { "Open", "Closed" };
		


		Mockito.when(agentRepository.findById(1)).thenReturn(Optional.of(agent));
//		Mockito.when(ticketRepository.findAllByCategoryIdInAndStatus(categorie, "Open")).thenReturn(tickets);
		Mockito.when(ticketRepository.findAllByCategoryIdInAndStatusIn(categorie, status)).thenReturn(tickets);

		List<Ticket> result = agentService.getSearch(1, searchCriteria);

		assertEquals(1, result.size());
		assertEquals(ticket, result.get(0));
	}



//	@Test
//	public void testIsNumericWithNumericStrings() {
//		assertTrue(agentCategoryServiceImpl.isNumeric("123"));
//	}
//
//	@Test
//	public void testIsNumericWithNonNumericStrings() {
//		assertFalse(agentCategoryServiceImpl.isNumeric("abc"));
//	}


}
