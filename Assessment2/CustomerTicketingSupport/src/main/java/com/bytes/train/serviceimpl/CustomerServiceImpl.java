package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	TicketRepository ticketRepository;

	@Override
	public Customer checkAccess(String userName, String password) {
		Customer userdata = customerRepository.findByUsername(userName);
		if ((userdata.getUsername().equals(userName)) && (userdata.getUserpassword().equals(password))) {
			return userdata;
		}
		return null;

	}
	@Override
	public List<Ticket> getTicketDetails(int id) {
		return ticketRepository.findByCustomerId(id);
	}
	
	@Override
	public String setTicketSatisfactoryRating(int ticketId, Float rating) {
		Ticket singleTicket=ticketRepository.findById(ticketId).orElseThrow();
		if(singleTicket.getStatus().equalsIgnoreCase("Closed")&& singleTicket.getSatisfactionRating()==0) {
		singleTicket.setSatisfactionRating(rating);
		ticketRepository.save(singleTicket);
		return "Ticket Rating is Being Sucessfully Added";
	}
		else {
			return "Rating Has  Being Added Already";
		}
	}
	
	
	//Customer Open and Close Tickets
	@Override
	public List<Ticket> getFilteredCustomerTickets(int cutsomerId,String status) {	
		System.out.println(cutsomerId);
		List<Ticket> filterTickets=ticketRepository.findByCustomer_customeridAndStatus(cutsomerId,status);
		return filterTickets;
	}
//	@Override
//	public List<Ticket> getClosedCustomerTickets(int customerId) {
//		List<Ticket> closeTickets=ticketRepository.findByCustomer_customeridAndStatus(customerId,"Closed");
//		return closeTickets;
//	}
	
	
}
