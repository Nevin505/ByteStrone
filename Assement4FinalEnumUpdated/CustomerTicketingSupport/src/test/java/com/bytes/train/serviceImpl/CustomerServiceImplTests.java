package com.bytes.train.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.StatusEnum;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.repos.UserRepository;
import com.bytes.train.service.CustomerService;
import com.bytes.train.service.UserService;

@SpringBootTest(classes = CustomerTicketingSupportApplication.class)
public class CustomerServiceImplTests {

	@MockBean
	private AgentRepository agentRepository;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	private CustomerService customerService;

	@MockBean
	private TicketRepository ticketRepository;

	@MockBean
	private CustomerRepository customerRepository;

	@Test
	public void testGetTicketDetails_Success() throws Exception {
		int customerId = 123;

		Customer customer = new Customer();
		customer.setId(customerId);

		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(1);
		ticket1.setCustomer(customer);

		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(2);
		ticket2.setCustomer(customer);

		List<Ticket> customerTickets = new ArrayList<>();
		customerTickets.add(ticket1);
		customerTickets.add(ticket2);

		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		when(ticketRepository.findByCustomer(customer)).thenReturn(customerTickets);

		List<Ticket> resultCustomerTickets = customerService.getTicketDetails(customerId);

		assertEquals(customerTickets, resultCustomerTickets);
		assertEquals(customerTickets.size(), resultCustomerTickets.size());
		assertEquals(ticket1, resultCustomerTickets.get(0));
		assertEquals(ticket2, resultCustomerTickets.get(1));
	}

	@Test
	public void testGetTicketDetails_ThrowException() throws Exception {
		int customerId = 123;

		Customer customer = new Customer();
		customer.setId(customerId);

		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(1);
		ticket1.setCustomer(customer);

		Ticket ticket2 = new Ticket();
		ticket2.setTicketId(2);
		ticket2.setCustomer(customer);

		List<Ticket> customerTickets = new ArrayList<>();
		customerTickets.add(ticket1);
		customerTickets.add(ticket2);

		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		when(ticketRepository.findByCustomer(customer)).thenReturn(customerTickets);

		int getCustomerTicket = 2; 

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			customerService.getTicketDetails(getCustomerTicket);
		});

		assertTrue(exception.getMessage().contains("There Exists No Customer"));
	}

	@Test
	public void testSetTicketSatisfactoryRating_Success() throws Exception {
		int ticketId = 1;
		float rating = 4.5f;

		Ticket closedTicket = new Ticket();
		closedTicket.setTicketId(ticketId);
		closedTicket.setStatus(StatusEnum.Closed);
		closedTicket.setSatisfactionRating(0.0f);

		Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(closedTicket));

		String result = customerService.setTicketSatisfactoryRating(ticketId, rating);

		assertEquals("Ticket Rating is Being Sucessfully Added", result);
		assertEquals(rating, closedTicket.getSatisfactionRating());
		Mockito.verify(ticketRepository, times(1)).save(closedTicket);
	}

	@Test
	public void testSetTicketSatisfactoryRating_TicketNotFound() {
		int nonExistentTicketId = 2;
		float rating = 4.5f;

		when(ticketRepository.findById(nonExistentTicketId)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> customerService.setTicketSatisfactoryRating(nonExistentTicketId, rating));

		assertEquals("No Ticket Is Found", exception.getMessage());
		verify(ticketRepository, times(0)).save(any());
	}

	@Test
	public void testGetFilteredCustomerTickets_Success() throws ResourceNotFoundException {
		int customerId = 1;
		StatusEnum status = StatusEnum.Closed;

		Customer customer = new Customer();
		customer.setId(customerId);

		List<Ticket> filteredTickets = new ArrayList<>();
		filteredTickets.add(new Ticket());
		filteredTickets.add(new Ticket());

		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		when(ticketRepository.findByCustomer_idAndStatus(customerId, status)).thenReturn(filteredTickets);

		List<Ticket> result = customerService.getFilteredCustomerTickets(customerId, status);

		assertEquals(filteredTickets.size(), result.size());
		verify(customerRepository, times(1)).findById(customerId);
		verify(ticketRepository, times(1)).findByCustomer_idAndStatus(customerId, status);
	}



}
//@Test
//public void testSetTicketSatisfactoryRating_RatingAlreadyAdded() throws Exception {
//	int ticketId = 1;
//	float initialRating = 3.0f;
//	float newRating = 4.5f;
//
//	Ticket closedTicketWithRating = new Ticket();
//	closedTicketWithRating.setTicketId(ticketId);
//	closedTicketWithRating.setStatus("Closed");
//	closedTicketWithRating.setSatisfactionRating(initialRating);
//
//	when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(closedTicketWithRating));
//
//	String result = customerService.setTicketSatisfactoryRating(ticketId, newRating);
//
//	assertEquals("Rating Has  Being Added Already", result);
//	assertEquals(initialRating, closedTicketWithRating.getSatisfactionRating()); // Ensure rating isn't changed
//	verify(ticketRepository, times(0)).save(any());
//}