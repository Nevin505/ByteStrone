package com.bytes.train.serviceimpl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.SearchCriteria;
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
	private CategoryRepository categoryRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Agent> getFullInfo() {
		return agentRepository.findAll();
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

	// To get the list of Tickets Which belongs to particular Categories
	@Override
	public List<Ticket> getParticularCategoryList(int agentId) throws Exception {
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("There Exists No Agent");
		}
		List<Category> agentCategories = agent.getCategory();
		System.out.println(agentCategories);
		String[] status = { "Open", "Closed" };
		List<Ticket> agentsTicketCatgory = ticketRepository.findAllByCategoryIdInAndStatusIn(agentCategories, status);
		System.out.println(agentsTicketCatgory);
		return agentsTicketCatgory;
	}

	@Override
	public List<Ticket> getfilteredTickets(int agentId, String status) throws Exception {
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
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("There Exist No Agent");
		}
		List<Category> category = agent.getCategory();
		if (searchCriteria.getStatus().equalsIgnoreCase("Open")||searchCriteria.getStatus().equalsIgnoreCase("Closed")) {
			String searchStatus = searchCriteria.getStatus();
			List<Ticket> searchResult = new LinkedList<>();
			List<Ticket> tickets = ticketRepository.findAllByCategoryIdInAndStatus(category,searchStatus);
			if (isNumeric(searchCriteria.getSubject())) {
				int ticketId = Integer.parseInt(searchCriteria.getSubject());
				searchResult = ticketRepository.findByCategoryIdInAndStatusAndTicketId(category,searchStatus,ticketId);
			} else {
				for (Ticket ticket : tickets) {
					if (ticket.getSubject().toLowerCase().contains(searchCriteria.getSubject().toLowerCase())) {
						searchResult.add(ticket);
					}
				}
			}
			return searchResult;
		} 
		else {
			String[] status = { "Open", "Closed" };
			List<Ticket> tickets = ticketRepository.findAllByCategoryIdInAndStatusIn(category, status);
			String search;
			search = searchCriteria.getSubject();
			List<Ticket> searchResult = new LinkedList<>();
			for (Ticket ticket : tickets) {
				if (isNumeric(search)) {
					int ticketId = Integer.parseInt(search);
					if (ticket.getTicketId() == ticketId) {
						searchResult.add(ticket);
						return searchResult;
					}
				} else {
					if ((ticket.getSubject().toLowerCase()).contains((searchCriteria.getSubject()).toLowerCase())) {
						searchResult.add(ticket);
					}
				}
			}
			return searchResult;
		}
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
				ticket.setStatus("Assigned");
				agent.setAgentCount(agent.getAgentCount() + 1);
				ticketRepository.save(ticket);
				agentRepository.save(agent);
				return "The Ticket Is Being Assigined";
			} else {
				return " Tickets Is Already Assigined";
			}
		} else {
			throw new Exception("This Agent is Not Associated With This Category");
		}
	}

	@Override
	public List<Ticket> getAssignedTickets(int agentId) throws Exception {
		Agent agent = agentRepository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("Agent Id Doen't Exist");
		}
		List<Ticket> tickets2 = ticketRepository.findByAgentIdAndStatus(agent, "Assigned");
		return tickets2;
	}

//	/To close The Tickets Which has Been Handled By That Agent
	@Override
	public Ticket closeTickets(int ticketid, int agentId) throws Exception {
		Ticket ticket = ticketRepository.findById(ticketid).orElse(null);
		if (ticket == null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		Agent agent = agentRepository.findById(ticket.getAgentId().getId()).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("There Is No Agent");
		}
		if (ticket.getAgentId().getId() == agentId && (ticket.getStatus().equals("Assigned"))) {
			ticket.setStatus("Closed");
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
	public List<Agent> getAgents(int id) throws Exception {
		Agent agent = agentRepository.findById(id).orElse(null);
		if (agent == null) {
			throw new ResourceNotFoundException("There Exists No Agent");
		}
		List<Category> categ = agent.getCategory();
		List<Agent> agentsCategory = agentRepository.findByCategoryIn(categ);
		return agentsCategory;

	}

	public void addCategoryToAgent(int agentId, int categoryId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		Category category = categoryRepository.findById(categoryId).orElseThrow();
		agent.getCategory().add(category);
		category.getAgentId().add(agent);
		agentRepository.save(agent);
		categoryRepository.save(category);
	}

	public void removeCategoryFromAgent(int agentId, int categoryId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		Category category = categoryRepository.findById(categoryId).orElseThrow();
		agent.getCategory().remove(category);
		category.getAgentId().remove(agent);
		agentRepository.save(agent);
		categoryRepository.save(category);
	}

	public List<Ticket> searchFullAgentTickets(int agentId, SearchCriteria searchCriteria) {

		Agent agent = agentRepository.findById(agentId).orElse(null);
		List<Category> category = agent.getCategory();
		List<Ticket> tickets = ticketRepository.findAllByCategoryIdIn(category);

		String search;
		search = searchCriteria.getSubject();

		List<Ticket> searchResult = new LinkedList<>();

		for (Ticket ticket : tickets) {
			if (isNumeric(search)) {
				int ticketId = Integer.parseInt(search);
				searchResult.add(ticketRepository.findByticketId(ticketId));
				return searchResult;
			} else {

				if ((ticket.getSubject().toLowerCase()).contains((searchCriteria.getSubject()).toLowerCase())) {
					searchResult.add(ticket);
//					return searchResult;
				}
			}

		}
		return searchResult;

	}

	private boolean isNumeric(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public List<Ticket> getAssigined() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Agent> getFullDetais() {

		return agentRepository.findAll();
	}

	@Override
	public List<Ticket> testService(int agentId, SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
//
//@Override
//public Agent checkAccess(String userName, String password) throws Exception {
//	Agent userdata = agentRepository.findByUserName(userName);
//	if(userdata==null) { 
//		throw new Exception("No Data Is THere"); 
//	}
//	if ((userdata.getUserName().equals(userName)) && (userdata.getUserPassword().equals(password))) {
//		
//		System.out.println("Getting data from db="+userdata);
//		return userdata;
//	}
//	
//	return null;
//}

//@Override
//public List<Ticket> getticketsOpen(int agentId) throws Exception {
//	Agent agent = agentRepository.findById(agentId).orElse(null);
//	if(agent==null) {
//		throw new ResourceNotFoundException("No Agent Id is Present");
//	}
//	List<Category> agentCategories = agent.getCategory();
//	List<Ticket> agentsTicketCatgory = ticketRepository.findAllByCategoryIdIn(agentCategories);
//	List<Ticket> OpenedTickets = new LinkedList<>();
//	for (Ticket ticket : agentsTicketCatgory) {
//		if (ticket.getStatus().equalsIgnoreCase("Open")) {
//			OpenedTickets.add(ticket);
//		}
//	}
//	return OpenedTickets;
//}

//@Override
//public List<Ticket> getclosedTickets(int agentId) throws Exception {
//	Agent agent = agentRepository.findById(agentId).orElse(null);
//	if(agent==null) {
//		throw new ResourceNotFoundException("No Agent Id is Present");
//	}
//	List<Category> category = agent.getCategory();
//	List<Ticket> tickets = ticketRepository.findAllByCategoryIdIn(category);
//	List<Ticket> closedTickets = new LinkedList<>();
//	for (Ticket ticket : tickets) {
//		if (ticket.getStatus().equalsIgnoreCase("Closed")) {
//			closedTickets.add(ticket);
//		}
//	}
//	return closedTickets;
//}
//
