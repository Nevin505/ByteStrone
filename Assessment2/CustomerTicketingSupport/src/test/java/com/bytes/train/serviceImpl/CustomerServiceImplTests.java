package com.bytes.train.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.Agent;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.serviceimpl.AgentCategoryServiceImpl;

@SpringBootTest(classes = CustomerTicketingSupportApplication.class)
public class CustomerServiceImplTests {
  
	@MockBean
	AgentRepository agentRepository;
	
	@Autowired
	AgentCategoryServiceImpl agentCategoryServiceImpl;
	  
	@Test
	void TestcheckAccessCorrect() {
		String name="n";
		String password="n";
	   Mockito.when(agentRepository.findByAgentName(name)).thenReturn(new Agent(0, "n",null, "n", null,null, null));
	  System.out.println();
	  assertEquals(name, agentCategoryServiceImpl.checkAccess(name, password).getAgentName());
	}
	
	@Test
	void TestcheckAccessNotCorrect() {
		String name="n";
		String password="k";
	   Mockito.when(agentRepository.findByAgentName(name)).thenReturn(new Agent(0, "n",null, "n", null,null, null));
	  System.out.println();
	  assertEquals(name, agentCategoryServiceImpl.checkAccess(name, password).getAgentName());
	}
	

    

    
}
