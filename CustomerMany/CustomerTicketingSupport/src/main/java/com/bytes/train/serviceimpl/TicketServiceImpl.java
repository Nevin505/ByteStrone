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
	CustomerRepository customerdao;

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
	public Ticket saveTicketPostman(int id, Ticket ticket) {
		Customer customer = customerdao.findById(id).orElseThrow();
		Category category = categoryRespository.findByCategoryName(ticket.getCategoryName());
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

	//To Assign Tickets To the Respective Agents
		@Override
		public void assignTickets(int ticketId, int agentId) {
			 Ticket ticket=ticketRepository.findByticketId(ticketId);
			 Agent agent=agentRespo.findById(agentId).orElse(null);
			 if(ticket.getAgentId()==null)
			 {
				ticket.setAgentId(agent); 
				ticket.setStatus("Assigined");
				ticketRepository.save(ticket);
			 }		 
			 else{
				 throw new IllegalArgumentException("Ticket or agent not found.");
			 }		
		}

		@Override
		public Ticket getCustomerSingleTicket(int ticketId) {
			
			return ticketRepository.findById(ticketId).orElse(null);
		}
}