package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;

public interface AgentCategoryService {

	public Agent checkAccess(String userName, String password) throws Exception;

	public List<Agent> getFullDetais();

	Ticket getSpecificTicketId(int id) throws Exception;

	public List<Ticket> getParticularCategoryList(int agentId);

	public Ticket closeTickets(int ticketid, int agentId) throws Exception;

	public List<Agent> getAgents(int id);

	public void addCategoryToAgent(int agentId, int categoryId);

	public void removeCategoryFromAgent(int agentId, int categoryId);

	public List<Agent> getFullInfo();

	public String assignToAgents(int ticketid, int agent) throws Exception;

	public List<Ticket> getAssignedTickets(int agentId) throws Exception;

	public List<Ticket> getAssigined();

	public List<Ticket> getclosedTickets(int agentId);

//	Added

//	public List<Ticket> getClosed(int agentId,SearchCriteria searchCriteria);

	public List<Ticket> getticketsOpen(int agentId);

	public List<Ticket> testService(int agentId, SearchCriteria searchCriteria);

	public List<Ticket> getSearch(int agentId, SearchCriteria searchCriteria);

}
