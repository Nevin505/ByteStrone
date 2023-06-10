package com.bytes.train.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;

public interface AgentService {
	public ResponseEntity<Agent> checkAccess(String userName,String password);

	public List<Ticket> findOpen();
	
	
//	public List<Ticket> getOpenTicketsSortedByStatus();
//	getOpenTicketsSortedByStatus();
	
	Ticket getSpecificTicketId(int id);
	
	public void assignTicketsLimit(int ticketId, int agentId);

	public List<Ticket> getParticularCategoryList(int agentId);
	
	public List<Ticket> getPrority();  
	
	public List<Agent> getAgentsList(Category category);
	
//	public String closeTicketsAgents();

	public void assignToAgents(int ticketid,int agent) throws Exception;
	
	public void closeTickets(int ticketid,int agentId) throws Exception;

	public List<Agent> getAgents(int id);
	
}