package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;

public interface AgentCategoryService {
	  
	public Agent checkAccess(String userName,String password);

	public List<Agent> getFullDetais();
	
	Ticket getSpecificTicketId(int id) ;
	
	public Ticket searchParticularCategoryList(int agentId,int ticketId);
	
	
	public List<Ticket> getParticularCategoryList(int agentId);
	
	public String closeTickets(int ticketid,int agentId) throws Exception;
		
	public List<Agent> getAgents(int id);
	
//	public List<Ticket> searchFullAgentTickets(int agentId, SearchCriteria searchCriteria);
	
//	public List<Ticket> getOpen(int agentId,SearchCriteria searchCriteria);
	

    public void addCategoryToAgent(int agentId, int categoryId);

    public void removeCategoryFromAgent(int agentId, int categoryId);
    
    public List<Agent> getFullInfo();

	

	public String assignToAgents(int ticketid,int agent) ;

    public List<Ticket> getAssignedTickets(int agentId);

	

	public List<Ticket> getAssigined();

	public List<Ticket> getclosedTickets(int agentId);
	
//	Added

//	public List<Ticket> getClosed(int agentId,SearchCriteria searchCriteria);

	public List<Ticket> getticketsOpen(int agentId);
	
	public List<Ticket> testService(int agentId,SearchCriteria searchCriteria);

	public List<Ticket> getSearch(int agentId, SearchCriteria searchCriteria);

	

	
	

	
	
	
	

	



	

}
