package com.bytes.train.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CategoryRespository;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketRepository ticketDao;

	@Autowired
	CustomerRepository customerdao;
	
	@Autowired
	UserloginServiceImpl UserloginServiceImpl;
	
	@Autowired
	AgentRepository agentRespo;
	
	@Autowired
	CategoryRespository categoryRespository;

	@Override
	public List<Ticket> getTicket() {
		return ticketDao.findAll();		
	}

	// Via Postman
	@Override
	public String saveTicketPostman(int id, Ticket ticket) {
		Customer customer = customerdao.findById(id).orElseThrow();
		Category category=categoryRespository.findByCategoryName(ticket.getCategoryName());
		ticket.setCategoryId(category);
		ticket.setCustomer(customer);
		ticketDao.save(ticket);		
		return "Ticket raised";
	}

	@Override
	@SuppressWarnings("deprecation")
	public void UpdateTicket(int id, Ticket ticket) {
		Ticket ticket2 = ticketDao.getById(id);
		ticket2.setSubject(ticket.getSubject());
		ticket2.getUpdated_Date();
		System.out.println(ticket2);
		ticketDao.save(ticket2);
	}

	@Override
	public void saveTicket(Ticket ticket) {
		System.out.println(ticket);
		ticketDao.save(ticket);
	}
	
//To Assign Tickets To the Respective Agents
	@Override
	public void assignTickets(int ticketId, int agentId) {
		 Ticket ticket=ticketDao.findByticketId(ticketId);
		 Agent agent=agentRespo.findById(agentId).orElse(null);
		 if(ticket.getAgentId()==null)
		 {
			ticket.setAgentId(agent); 
			ticket.setStatus("Hnadles");
			ticketDao.save(ticket);
		 }		 
		 else {
			 throw new IllegalArgumentException("Ticket or agent not found.");
		 }		
	}

	@Override
	public List<Category> optionsvalue() {
		
		 return categoryRespository.findAll();
		
		
	}


	

}
