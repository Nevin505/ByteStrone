package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Category;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CategoryRepository;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.TicketService;

import com.bytes.train.entities.Agent;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AgentRepository agentRespo;

	@Autowired
	CategoryRepository categoryRespository;

	@Override
	public List<Ticket> getTicket() {
		return ticketRepository.findAll();
	}

	// Via Postman
	@Override
	public Ticket saveTicketPostman(int id, Ticket ticket) throws Exception {
		Customer customer = customerRepository.findById(id).orElseThrow();
		Category category = categoryRespository.findByCategoryName(ticket.getCategoryName());
		if(ticket.getPriority()==null || ticket.getSubject()==null || ticket.getDescription()==null) {
			throw new Exception("Please Enter Details");
		}
		System.out.println(customer);
		System.out.println(category);
		ticket.setCategoryId(category);
		ticket.setCustomer(customer);
		System.out.println(ticket);
		return ticketRepository.save(ticket);
	}

	@Override
	public List<Category> optionsvalue() {
		return categoryRespository.findAll();
	}

	@Override
	public Ticket getCustomerSingleTicket(int ticketId) {
		return ticketRepository.findById(ticketId).orElse(null);
	}

}
