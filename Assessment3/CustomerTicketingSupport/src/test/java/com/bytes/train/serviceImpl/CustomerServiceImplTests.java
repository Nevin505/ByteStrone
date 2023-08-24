package com.bytes.train.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.repos.UserRepository;
import com.bytes.train.service.AgentCategoryService;
import com.bytes.train.service.UserService;
import com.bytes.train.serviceimpl.CustomerServiceImpl;

@SpringBootTest(classes = CustomerTicketingSupportApplication.class)
public class CustomerServiceImplTests {

	@MockBean
	private AgentRepository agentRepository;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private AgentCategoryService agentCategoryServiceImpl;

	@Autowired
	UserService userService;
	
	@Autowired
	private CustomerServiceImpl customerServiceImplTests;

	@MockBean
	private TicketRepository ticketRepository;

	static List<Ticket>  getTicketDetailsss() {
		Customer customer = new Customer();
		customer.setId(123);
		customer.setName("Arjun");
		customer.setUserPassword("Arjun");
		customer.setEmail("arjun@gmail.com");
		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(10);
		ticket1.setSubject("Camera");
		ticket1.setDescription("Camera Lens  Not Working");
		ticket1.setPriority("Medium");
		ticket1.setStatus("Assigned");
		ticket1.setCreation_Date(new Date());
		ticket1.setCustomer(customer);
		
		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(11);
		ticket2.setSubject("Heating Issue");
		ticket2.setDescription("The sensor is heating Up so  Much");
		ticket2.setPriority("High");
		ticket2.setStatus("Open");
		ticket2.setCreation_Date(new Date());
		ticket2.setCustomer(customer);
		
		 
		return  Arrays.asList(ticket1);
	}

	@Test
	public void testgetTicketDetails(int id) {
		        // Arrange
		        int customerId = 123;
		        List<Ticket> tickets =CustomerServiceImplTests.getTicketDetailsss();

		        Mockito.when(ticketRepository.findByCustomer_id(customerId)).thenReturn(tickets);

		        // Act
		        List<Ticket> result = customerServiceImplTests.getTicketDetails(customerId);

		        // Assert
		        assertEquals(tickets, result);
		        verify(ticketRepository, times(1)).findByCustomer_id(customerId);
		    }
		
	}


