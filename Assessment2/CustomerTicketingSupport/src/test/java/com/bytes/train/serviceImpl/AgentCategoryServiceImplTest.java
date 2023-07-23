package com.bytes.train.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
public class AgentCategoryServiceImplTest {
	@MockBean
	AgentRepository agentRepository;

	@Autowired
	AgentCategoryService agentCategoryServiceImpl;

	@Autowired
	private AgentCategoryService agentService;

	@MockBean
	private TicketRepository ticketRepository;

	static Agent getDetails() {
		int agentId = 123;
		Agent agent = new Agent();
		agent.setAgentID(agentId);
		agent.setAddress(null);
		agent.setAgentName(null);
		return agent;
	}

	static List<Category> getCategoryDetails() {

		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategory_name("Hardware");

		Category category2 = new Category();
		category2.setCategoryId(2);
		category2.setCategory_name("Software");

		return Arrays.asList(category1, category2);
	}

	@Test
//	@Disabled
	void TestCheckAgentParticularList() {
		int agentId = 123;

		Agent agent = AgentCategoryServiceImplTest.getDetails();

		List<Category> categories = AgentCategoryServiceImplTest.getCategoryDetails();
		agent.setCategory(categories);

		Category category1 = categories.get(0);

		Category category2 = categories.get(1);

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

		Mockito.when(ticketRepository.findAllByCategoryIdInAndStatusIn(anyList(), any()))
				.thenReturn(List.of(ticket1, ticket2));

		List<Ticket> agentTickets = agentService.getParticularCategoryList(agentId);

		System.out.println(agentTickets.size());

		assertEquals(2, agentTickets.size());
		assertEquals(2, agentTickets.size());
		assertEquals(ticket1, agentTickets.get(0));
		assertEquals(ticket2, agentTickets.get(1));

	}

//
	@Test
	@Disabled
	public void testGetParticularCategoryListWithNoMatchingTickets() {
		// Test data setup
		int agentId = 1;

		String[] Status = { "Open", "Closed" };

		Agent agent1 = AgentCategoryServiceImplTest.getDetails();

		List<Category> categories = AgentCategoryServiceImplTest.getCategoryDetails();

		agent1.setCategory(categories);

		List<Ticket> emptyTicketList = new ArrayList<>();

		List<Ticket> tickets = new ArrayList<>();
		tickets.add(new Ticket(4, "Ticket4", null, null, null, null, null, null, null, null,
				new Category(204, null, null), 0f, null));
		tickets.add(new Ticket(1, "Ticket1", null, null, null, null, null, null, null, null,
				new Category(201, "CategoryX", null), 0f, "Open"));
		tickets.add(new Ticket(2, "Ticket2", null, null, null, null, null, null, null, null,
				new Category(202, "CategoryY", null), 0f, "Closed"));
		tickets.add(new Ticket(3, "Ticket3", null, null, null, null, null, null, null, null,
				new Category(203, "CategoryZ", null), 0f, "Open"));

		Mockito.when(agentRepository.findById(agentId)).thenReturn(Optional.of(agent1));

		Mockito.when(ticketRepository.findAllByCategoryIdInAndStatusIn(anyList(), any())).thenReturn(emptyTicketList);

		List<Ticket> result = agentService.getParticularCategoryList(agentId);

		assertEquals(0, result.size());
	}

//	Pass Test
	@Test
	@Disabled
	public void testAssiginTickets() throws Exception {
		Agent agent = AgentCategoryServiceImplTest.getDetails();
		List<Category> categories = AgentCategoryServiceImplTest.getCategoryDetails();
		Category category1 = categories.get(0);
		Category category2 = categories.get(1);

		agent.setCategory(Arrays.asList(category1, category2));

		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(101);
		ticket1.setCategoryId(category1);
		ticket1.setStatus("Open");
		ticket1.setAgentId(null);

		Mockito.when(ticketRepository.findById(101)).thenReturn(Optional.of(ticket1));
		Mockito.when(agentRepository.findById(123)).thenReturn(Optional.of(agent));

		String result = agentService.assignToAgents(101, 123);

		assertEquals("The Ticket Is Being Assigined", result);

		assertEquals(agent, ticket1.getAgentId());
		assertEquals("Assigined", ticket1.getStatus());
		verify(ticketRepository, times(1)).save(ticket1);
	}

	@Test
	@Disabled
	public void testNotAssiginTicketsDiffCategoryAgents() throws Exception {
		Agent agent = AgentCategoryServiceImplTest.getDetails();
		List<Category> categories = AgentCategoryServiceImplTest.getCategoryDetails();
		Category category1 = categories.get(0);
		Category category2 = categories.get(1);

		agent.setCategory(Arrays.asList(category1));

		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(101);
		ticket1.setCategoryId(category2);
		ticket1.setStatus("Open");
		ticket1.setAgentId(null);

		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(102);
		ticket2.setCategoryId(category2);
		ticket2.setStatus("Open");
		ticket2.setAgentId(null);

		Mockito.when(ticketRepository.findById(101)).thenReturn(Optional.of(ticket1));
		Mockito.when(agentRepository.findById(123)).thenReturn(Optional.of(agent));

		String result = agentService.assignToAgents(101, 123);

		try {
			agentService.assignToAgents(101, 123);
			fail("Exception should be thrown for an invalid assignment.");
		} catch (Exception e) {
			// Assert the type and message of the thrown exception
			assertEquals("This Agent is Not Associated With This Category", e.getMessage());
			assertTrue(e instanceof Exception); // Make sure it is the correct type of exception
		}

	}

	@Test
	@Disabled
	public void testAssiginedTicketsAgent() throws Exception {
		Agent agent = AgentCategoryServiceImplTest.getDetails();
		List<Category> categories = AgentCategoryServiceImplTest.getCategoryDetails();
		Category category1 = categories.get(0);
		Category category2 = categories.get(1);

		agent.setCategory(Arrays.asList(category1));

		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(101);
		ticket1.setCategoryId(category1);
		ticket1.setStatus("Open");
		ticket1.setAgentId(agent);

		Mockito.when(ticketRepository.findById(101)).thenReturn(Optional.of(ticket1));
		Mockito.when(agentRepository.findById(123)).thenReturn(Optional.of(agent));

		String result = agentService.assignToAgents(101, 123);

		assertEquals(" Tickets Is Already Assigined", result);
		assertEquals(agent, ticket1.getAgentId());

	}

}

//Agent agent1 = new Agent();
//agent1.setAgentID(agentId);
//agent1.setAddress(null);
//agent1.setAgentName(null);

//Category category1 = new Category();
//category1.setCategoryId(1);
//category1.setCategory_name("Hardware");
//
//Category category2 = new Category();
//category2.setCategoryId(2);
//category2.setCategory_name("Software");

//Category category1 = new Category();
//category1.setCategoryId(1);
//category1.setCategory_name("Hardware");
//
//Category category2 = new Category();
//category2.setCategoryId(2);
//category2.setCategory_name("Software");
//
//agent.setCategory(Arrays.asList(category1, category2));

//Agent agent = new Agent();
//agent.setAgentID(agentId);
//agent.setAddress(null);
//agent.setAgentName(null);
//    agent
