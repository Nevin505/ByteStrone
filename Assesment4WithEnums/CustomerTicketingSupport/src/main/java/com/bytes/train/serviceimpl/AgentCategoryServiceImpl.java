package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.StatusEnum;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CategoryRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.AgentCategoryService;

@Service
public class AgentCategoryServiceImpl implements AgentCategoryService {

	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired CategoryRepository categoryRepository;

	// To get the list of Tickets Which belongs to particular Categories
	@Override
	public List<Ticket> getParticularCategoryList(int agentId) throws Exception{
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (agent == null) { 
			throw new ResourceNotFoundException("There Exists No Agent");
		}
		List<Category> agentCategories = agent.getCategory();
		System.out.println(agentCategories);
		StatusEnum[] status = { StatusEnum.Open,StatusEnum.Closed };
		List<Ticket> agentsTicketCatgory = ticketRepository.findAllByCategoryIdInAndStatusIn(agentCategories, status);
		System.out.println(agentsTicketCatgory);
		return agentsTicketCatgory; 
	}
	
	@Override
	public List<Ticket> getfilteredTickets(int agentId, StatusEnum status) throws Exception {
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("No Agent Id is Present");
		}  
		List<Category> agentCategories = agent.getCategory();
		List<Ticket> filteredTickets = ticketRepository.findAllByCategoryIdInAndStatus(agentCategories, status);
		return filteredTickets;
	}

	@Override
	public List<Ticket> getSearch(int agentId, SearchCriteria searchCriteria) throws Exception {
		System.out.println(searchCriteria.getTicketId());
		System.out.println(searchCriteria.getTicketId());
		StatusEnum statusTicket = StatusEnum.valueOf(searchCriteria.getStatus());
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("There Exist No Agent");
		}
		List<Category> category = agent.getCategory();
		if ((StatusEnum.Open.name()).equals(searchCriteria.getStatus())||(StatusEnum.Closed.name()).equals(searchCriteria.getStatus())) {
			String serachTicketSubject=searchCriteria.getSubject();
			int serachTicketId=searchCriteria.getTicketId();
			if(searchCriteria.getSubject().trim().length()!=0 && searchCriteria.getTicketId()>0) {
				 return ticketRepository.findByCategoryIdInAndStatusAndSubjectIgnoreCaseContainingOrCategoryIdInAndTicketIdAndStatus(category,statusTicket,serachTicketSubject,category,serachTicketId,statusTicket);
			}
			else if(searchCriteria.getSubject().trim().length()!=0) {
				return ticketRepository.findByCategoryIdInAndStatusAndSubjectIgnoreCaseContaining(category,statusTicket,serachTicketSubject);				
			}
			else {
				return ticketRepository.findByCategoryIdInAndStatusAndTicketId(category,statusTicket,serachTicketId);				
			}				
		} 
		else {
			StatusEnum[] status = {StatusEnum.Open,StatusEnum.Closed};
			String serachTicketSubject=searchCriteria.getSubject();
			if(serachTicketSubject.trim().length()!=0 && searchCriteria.getTicketId()>0) {
				int serachTicketId=searchCriteria.getTicketId();
				System.out.println("Here it is");
				return ticketRepository.findAllByCategoryIdInAndStatusInAndAndSubjectIgnoreCaseContainingOrCategoryIdInAndStatusInAndTicketId(category,status,serachTicketSubject,category,status,serachTicketId);				
			}
			else if(serachTicketSubject.trim().length()!=0) {
				return ticketRepository.findAllByCategoryIdInAndStatusInAndAndSubjectIgnoreCaseContaining(category,status,serachTicketSubject);				
			}
			else {
				int serachTicketId=searchCriteria.getTicketId();
				return ticketRepository.findAllByCategoryIdInAndStatusInAndAndTicketId(category,status,serachTicketId);
			}
			
		}
	}
	
	@Override
	public List<Agent> getAgents(int id) throws Exception {
		Agent agent = agentRepository.findById(id).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("There Exists No Agent");
		}
		List<Category> categ = agent.getCategory();
		List<Agent> agentsCategory = agentRepository.findByCategoryIn(categ);
		return agentsCategory;

	}

	@Override 
	public String assignToAgents(int ticketId, int agentId) throws Exception {
		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (ticket == null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		if (agent == null) {
			throw new ResourceNotFoundException("There Exists No Agent");
		}
		List<Category> agent1 = agent.getCategory();
		if (agent1.contains(ticket.getCategoryId())) {
			if (ticket.getAgentId() == null) {
				ticket.setAgentId(agent);
				ticket.setStatus(StatusEnum.Assigned);
				agent.setAgentCount(agent.getAgentCount() + 1);
				ticketRepository.save(ticket);
				agentRepository.save(agent);
				return "The Ticket Is Being Assigined";
			} else {
				return "Tickets Is Already Assigined";
			}
		} else {
			throw new Exception("This Agent is Not Associated With This Category");
		}
	}

//	/To close The Tickets Which has Been Handled By That Agent
	@Override
	public Ticket closeTickets(int ticketid, int agentId) throws Exception {
		Ticket ticket = ticketRepository.findById(ticketid).orElse(null);
		if (ticket == null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("There Is No Agent");
		 }
		if(StatusEnum.Closed.equals(ticket.getStatus())) {
			return ticket;
		}
		if (ticket.getAgentId().getId() == agentId && (ticket.getStatus().equals(StatusEnum.Assigned ))) {
			ticket.setStatus(StatusEnum.Closed);
			ticket.closeTicket();
			agent.setAgentCount(agent.getAgentCount() - 1);
			agentRepository.save(agent);
			ticketRepository.save(ticket);
			return ticket;
		} else {
			return null;
		}
	} 

	@Override
	public List<Ticket> getAssignedTickets(int agentId) throws Exception {
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("Agent Id Doen't Exist");
		}
		List<Ticket> tickets2 = ticketRepository.findByAgentIdAndStatus(agent,StatusEnum.Assigned);
		return tickets2; 
	}

// To find a Ticket By Specific Ticket Id
	@Override
	public Ticket getSpecificTicketId(int ticketId) throws Exception {
		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		if (ticket == null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		return ticket;
	}

	
//	@Override 
//	public List<Agent> getFullDetais() {
//		return agentRepository.findAll();
//	}
//	
	public void addCategoryToAgent(int agentId, int categoryId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		Category category = categoryRepository.findById(categoryId).orElseThrow();
		agent.getCategory().add(category);
		category.getAgentId().add(agent);
		agentRepository.save(agent);
		categoryRepository.save(category);
	}
//
//	public void removeCategoryFromAgent(int agentId, int categoryId) {
//		Agent agent = agentRepository.findById(agentId).orElseThrow();
//		Category category = categoryRepository.findById(categoryId).orElseThrow();
//		agent.getCategory().remove(category);
//		category.getAgentId().remove(agent);
//		agentRepository.save(agent);
//		categoryRepository.save(category);
//	}
//	
//	@Override 
//	public List<Agent> getFullInfo() {
//		return agentRepository.findAll();
//	}
//
//	@Override
//	public List<Ticket> testService(int agentId, SearchCriteria searchCriteria) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
