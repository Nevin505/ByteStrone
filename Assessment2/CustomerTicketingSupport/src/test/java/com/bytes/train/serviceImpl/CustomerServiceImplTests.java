package com.bytes.train.serviceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
public class CustomerServiceImplTests {

	@MockBean
	private AgentRepository agentRepository;

	@Autowired
	private AgentCategoryService agentCategoryServiceImpl;


	@MockBean
	private TicketRepository ticketRepository;


//    Login Test Case
	@Test
	void TestcheckAccessCorrect() throws Exception {
		String name = "n";
		String password = "n";
		Mockito.when(agentRepository.findByAgentName(name)).thenReturn(new Agent(0, "n", null, "n", null, null, null));
		System.out.println();
		assertEquals(password, agentCategoryServiceImpl.checkAccess(name, password).getAgentPassword());
	}

//  Login Test Case
	@Test
	@Disabled
	void TestcheckAccessNotCorrectPassword() throws Exception {
		CustomerServiceImplTests customer = new CustomerServiceImplTests();
		String name = "n";
		String password = "k";
		Mockito.when(agentRepository.findByAgentName(name)).thenReturn(new Agent(0, "n", null, "k", null, null, null));
		System.out.println();
		assertEquals(password, agentCategoryServiceImpl.checkAccess(name, password).getAgentPassword());
	}

//Login Test Case
	@Test
	@Disabled
	void TestcheckAccessNoData() throws Exception {
		String name = "k";
		String password = "k";
		Mockito.when(agentRepository.findByAgentName(name)).thenReturn(null);
		System.out.println();
		assertEquals(password, agentCategoryServiceImpl.checkAccess(name, password).getAgentPassword());
	}

}


////
//Agent agentDetails() {
//	Agent agent = new Agent();
//	agent.setAgentName("a");
//	agent.setAgentPassword("a");
//	return agent;
//}
