package com.bytes.train.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CategoryRepository;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.AgentCategoryService;
import com.bytes.train.service.TicketService;

@SpringBootTest(classes = CustomerTicketingSupportApplication.class)
public class TicketServiceImplTest {

	@MockBean
	private AgentRepository agentRepository;

	@Autowired
	private TicketService ticketservice;

	@Autowired
	private AgentCategoryService agentCategoryServiceImpl;

	@Autowired
	private AgentCategoryService agentCategoryService;

	@MockBean
	private TicketRepository ticketRepository;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private CategoryRepository categoryRepository;

	@Test
	@Disabled
	public void testSaveTicketPostman() throws Exception {

		Customer customer = new Customer();
		customer.setCustomerid(10);

		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategory_name("Hardware");

		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(101);
		ticket1.setCategoryId(category1);
		ticket1.setPriority("Medium");
		ticket1.setSubject("Camera");
		ticket1.setDescription("The Clarity is not upto the mark");
		ticket1.setStatus("Open");
		ticket1.setCategoryName("Hardware");

		Mockito.when(customerRepository.findById(10)).thenReturn(Optional.of(customer));
		Mockito.when(categoryRepository.findByCategoryName(ticket1.getCategoryName())).thenReturn(category1);
		Mockito.when(ticketRepository.save(ticket1)).thenReturn(ticket1);

		Ticket returnTicket = ticketservice.saveTicketPostman(10, ticket1);

		assertEquals("Hardware", returnTicket.getCategoryId().getCategory_name());
		verify(customerRepository, times(1)).findById(10);
		verify(categoryRepository, times(1)).findByCategoryName("Hardware");
		verify(ticketRepository, times(1)).save(ticket1);
	}
	
	
	
	@Test
	public void testNotSaveTicketPostman() throws Exception {

		Customer customer = new Customer();
		customer.setCustomerid(10);

		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setCategory_name("Hardware");

		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(101);
		ticket1.setCategoryId(category1);
		ticket1.setPriority(null);
		ticket1.setSubject("Camera");
		ticket1.setDescription(null);
		ticket1.setStatus("Open");
		ticket1.setCategoryName("Hardware");

		Mockito.when(customerRepository.findById(10)).thenReturn(Optional.of(customer));
		Mockito.when(categoryRepository.findByCategoryName(ticket1.getCategoryName())).thenReturn(category1);
		Mockito.when(ticketRepository.save(ticket1)).thenReturn(ticket1);

		Ticket returnTicket = ticketservice.saveTicketPostman(10, ticket1);

		assertEquals("Hardware", returnTicket.getCategoryId().getCategory_name());
		verify(customerRepository, times(1)).findById(10);
		verify(categoryRepository, times(1)).findByCategoryName("Hardware");
		verify(ticketRepository, times(1)).save(ticket1);
	}



}
