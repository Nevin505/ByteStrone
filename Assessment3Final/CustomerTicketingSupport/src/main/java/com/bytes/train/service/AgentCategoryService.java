package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;

public interface AgentCategoryService {

	Ticket getSpecificTicketId(int id) throws Exception;

	public List<Ticket> getParticularCategoryList(int agentId) throws Exception;

	public Ticket closeTickets(int ticketid, int agentId) throws Exception;

	public List<Agent> getAgents(int id) throws Exception;

	public String assignToAgents(int ticketid, int agent) throws Exception;

	public List<Ticket> getAssignedTickets(int agentId) throws Exception;

	public List<Ticket> getSearch(int agentId, SearchCriteria searchCriteria) throws Exception;

	public List<Ticket> getfilteredTickets(int agentId, String status) throws Exception;

//	public void addCategoryToAgent(int agentId, int categoryId);
//
//	public void removeCategoryFromAgent(int agentId, int categoryId);

//	public List<Agent> getFullInfo();

//	public List<Ticket> testService(int agentId, SearchCriteria searchCriteria);
//	public List<Agent> getFullDetais();
}
