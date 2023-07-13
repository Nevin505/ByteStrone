package com.bytes.train.service;



import java.util.List;

import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;

public interface TicketService {
	    

	
	public List<Ticket> getTicket();
	

	Ticket saveTicketPostman(int id, Ticket ticket);
  
	public List<Category> optionsvalue();


	//To Assign Tickets to The Agents
	public void assignTickets(int ticketId, int agentId);


	public Ticket getCustomerSingleTicket(int ticketId);


	
	
}
