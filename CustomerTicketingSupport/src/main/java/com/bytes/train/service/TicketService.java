package com.bytes.train.service;



import java.util.List;
import java.util.Map;

import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;

public interface TicketService {
	 
	public List<Ticket> getTicket();
	 public void UpdateTicket(int id,Ticket ticket); //UpdateTicket
//	 public String saveTicket(int id, Ticket ticket);
	 public void saveTicket(Ticket ticket);
	 
	String saveTicketPostman(int id, Ticket ticket);
	
	//To Assign Tickets to The Agents
	public void assignTickets(int ticketId, int agentId);
	
	public List<Category> optionsvalue();
	
	public Map<String, Integer> getVolume();
	
	public Map<String, Long> getResponseTime();
	
}
