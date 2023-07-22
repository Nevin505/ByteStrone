package com.bytes.train.serviceimpl;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;
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
		if(ticket==null) {
			throw new Exception("There is No Ticket");
		}
		return ticket;
	}

	// To get the list of Tickets Which belongs to particular Categories
	@Override
	public List<Ticket> getParticularCategoryList(int agentId) { 
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		List<Category> agentCategories = agent.getCategory();
		System.out.println(agentCategories);
		String[] status={"Open","Closed"};
		List<Ticket> agentsTicketCatgory = ticketRepository.findAllByCategoryIdInAndStatusIn(agentCategories,status);
		return agentsTicketCatgory;
	}

	@Override
	public List<Ticket> getticketsOpen(int agentId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		List<Category> agentCategories = agent.getCategory();
		List<Ticket> agentsTicketCatgory = ticketRepository.findAllByCategoryIdIn(agentCategories);
		List<Ticket> OpenedTickets = new LinkedList<>();
		for (Ticket ticket : agentsTicketCatgory) {
			if (ticket.getStatus().equalsIgnoreCase("Open")) {
				OpenedTickets.add(ticket);
			}
		}
		return OpenedTickets;
	}
	
	@Override
	public List<Ticket> getclosedTickets(int agentId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		List<Category> category = agent.getCategory();
		List<Ticket> tickets = ticketRepository.findAllByCategoryIdIn(category);
		List<Ticket> closedTickets = new LinkedList<>();
		for (Ticket ticket : tickets) {
			if (ticket.getStatus().equalsIgnoreCase("Closed")) {
				closedTickets.add(ticket);
			}
		}
		return closedTickets;
	}


	
	
	@Override
	public List<Ticket> getSearch(int agentId, SearchCriteria searchCriteria) {
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		System.out.println(searchCriteria.getStatus());
		List<Category> category = agent.getCategory();
		if (searchCriteria.getStatus().equalsIgnoreCase("Open")) {
			String serachStatus = searchCriteria.getStatus();
			List<Ticket> searchResult = new LinkedList<>();
			List<Ticket> tickets = ticketRepository.findAllByCategoryIdInAndStatus(category, serachStatus);
			System.out.println(tickets);
			if (isNumeric(searchCriteria.getSubject())) {
				int ticketId = Integer.parseInt(searchCriteria.getSubject());
				for (Ticket ticket : tickets) {
					if (ticket.getTicketId() == ticketId) {
						searchResult.add(ticket);
						return searchResult;
					}
				}
			} else {
				for (Ticket ticket : tickets) {
					if (ticket.getSubject().contains(searchCriteria.getSubject())) {
						searchResult.add(ticket);
					}
				}
			}

			return searchResult;

		} else if (searchCriteria.getStatus().equalsIgnoreCase("closed")) {
			List<Ticket> searchResult = new LinkedList<>();
			List<Ticket> tickets = ticketRepository.findAllByCategoryIdInAndStatus(category,
					searchCriteria.getStatus());
			if (isNumeric(searchCriteria.getSubject())) {
				int ticketId = Integer.parseInt(searchCriteria.getSubject());
				for (Ticket ticket : tickets) {
					if (ticket.getTicketId() == ticketId) {
						searchResult.add(ticket);
						return searchResult;
					}
				}
			} else {
				for (Ticket ticket : tickets) {
					if (ticket.getSubject().contains(searchCriteria.getSubject())) {
						searchResult.add(ticket);
					}
				}

			}

			return searchResult;

		} else {
			
			System.out.println("Here");
			Agent agents = agentRepository.findById(agentId).orElse(null);
			List<Category> categorys = agent.getCategory();
			String[] status={"Open","Closed"};
			List<Ticket> tickets = ticketRepository.findAllByCategoryIdInAndStatusIn(category,status);

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
       
		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		List<Category> agent1 = agent.getCategory();		
		if(agent1.contains(ticket.getCategoryId())) {
				if ( ticket.getAgentId() == null) {
						ticket.setAgentId(agent);
						ticket.setStatus("Assigined");
						ticketRepository.save(ticket);
						return "The Ticket Is Being Assigined";
				}
				else {
					return " Tickets Is Already Assigined";			
			}							
		}else {
			throw new Exception("This Agent is Not Associated With This Category");
		}		
	}

	@Override
	public List<Ticket> getAssignedTickets(int agentId) throws Exception {		
		Agent agent=agentRepository.findById(agentId).orElseThrow();
		if(agent==null) {
			throw new Exception("Agent Id Doen't Exist");
		}
		List<Ticket> tickets2=ticketRepository.findByAgentIdAndStatus(agent, "Assigined");
		System.out.println("By Using Jpa Repository");
		System.out.println(tickets2);
		return tickets2;
	}

//	/To close The Tickets Which has Been Handled By That Agent
	@Override
	public Ticket closeTickets(int ticketid, int agentId)  {
		Ticket ticket = ticketRepository.findById(ticketid).orElseThrow();
		if (ticket.getAgentId().getAgentID() == agentId && (ticket.getStatus().equals("Assigined"))) {
			ticket.setStatus("Closed");
			ticket.closeTicket();
			ticketRepository.save(ticket);
			return ticket;
		} else {
			return null;
		}
	}

	@Override
	public List<Agent> getAgents(int id) {		
		Agent agent = agentRepository.findById(id).orElseThrow();
		List<Category> categ = agent.getCategory();
		List<Agent> agentsCategory=agentRepository.findByCategoryIn(categ);
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

	@Override
	public Agent checkAccess(String userName, String password) throws Exception {
		Agent userdata = agentRepository.findByAgentName(userName);
		if(userdata==null) { 
			throw new Exception("No Data Is THere"); 
		}
		if ((userdata.getAgentName().equals(userName)) && (userdata.getAgentPassword().equals(password))) {
			
			System.out.println("Getting data from db="+userdata);
			return userdata;
		}
		
		return null;
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

//@Override
//public List<Ticket> testService(int agentId, SearchCriteria searchCriteria) {
//	// getting agent cateogry
//	Agent agent = agentRepository.findById(agentId).orElse(null);
//	List<Category> category = agent.getCategory();
//	String status = searchCriteria.getStatus();
//
////	if(status!="Reset") {
//	List<Ticket> tickets = ticketRepository.findAllByCategoryIdIn(category);
////	}else {
//
////	}
//
//	return tickets;
//}

