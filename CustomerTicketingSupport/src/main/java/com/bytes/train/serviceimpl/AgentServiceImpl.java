package com.bytes.train.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRespo;
import com.bytes.train.repos.CategoryRespository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService {
	@Autowired
	TicketRepository ticketDao;

	@Autowired
	AgentRespo agentRespo;

	@Autowired
	CategoryRespository categoryRespository;

//For Agent Login Purpose
	@Override
	public ResponseEntity<Agent> checkAccess(String userName, String password) {

		List<Agent> userdata = agentRespo.findAll();
		for (Agent userLogin : userdata) {
			if ((userLogin.getAgentName().equals(userName)) && (userLogin.getAgentPassword().equals(password))) {
				return ResponseEntity.ok(userLogin);
			}
		}
		return ResponseEntity.ok(null);

	}

// To Find All The Open Tickets
	@Override
	public List<Ticket> findOpen() {
		return ticketDao.findOpenTickets();
	}

//To find a Ticket By Specific Ticket Id
	@Override
	public Ticket getSpecificTicketId(int id) {
		return ticketDao.findById(id).orElse(null);
	}

//To get the list of Tickets Which belongs to  particular Categories
	@Override
	public List<Ticket> getParticularCategoryList(int agentId) {

		Agent agent = agentRespo.findById(agentId).orElse(null);
		Category category = agent.getCategory();
		int var1 = category.getCategoryId();
		return ticketDao.findByCategoryId_CategoryId(var1);
	}

//	Assign Tickets To Agent
	@Override
	public void assignTickets(int ticketId, int agentId) {
		Ticket ticket = ticketDao.findByticketId(ticketId);
		Agent agent = agentRespo.findById(agentId).orElse(null);
		if (ticket.getAgentId()== null) {
			ticket.setAgentId(agent);
			ticket.setStatus("Hnadles");
			ticketDao.save(ticket);
		}
			else if(ticket.getStatus().equals("Hnadles")) {
				ticket.setClosedDate(new Date());
				ticket.setStatus("Closed");
				ticketDao.save(ticket);				
			}				
		 else {
			throw new IllegalArgumentException("Ticket or agent not found.");
		}
	}	
//
	@Override
	public List<Agent> getAgentsList(Category category) {
		agentRespo.findByCategory(category);
		return null;
	}

//	@Override
//	public String closeTicketsAgents() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//  Ticket Assignination
	
	
	
	@Override
	public void assignToAgents(int ticketId, int agentId) throws Exception {
	    Ticket ticket = ticketDao.findById(ticketId).orElse(null);
	    Ticket ticket2=ticketDao.findById(ticketId).orElse(null);
//	    System.out.println("Hai from here");
	    if (ticket == null) {
	        System.out.println("No tickets found for that particular details");
	        throw new Exception("No tickets found for the specified details");
	    }
	    
	    int ticketCategoryId = ticket.getCategoryId().getCategoryId();
	    System.out.println(ticketCategoryId);	    
	    Agent agent = agentRespo.findById(agentId).orElse(null);	    
	    if (agent == null) {
	        System.out.println("Invalid Agent Id");
	        throw new Exception("No Agents were found ");
	    }	    
	    int agentCategoryId = agent.getCategory().getCategoryId();		
	    if (ticketCategoryId == agentCategoryId) {
	        System.out.println("Same");	
	        if(ticket.getAgentId()==null){
	        	ticket.setAgentId(agent);
		        ticket.setStatus("Handled");
		        ticketDao.save(ticket);
	        }
	        else {
	        	throw new Exception("Agent is already assigned");

	        }
	        
	    } else {
	        System.out.println("Wrong");
	        throw new Exception("No Agents were found for the particular Category");
	    }
	}


//To close The Tickets Which has Been Handled By That Agent
@Override
public void closeTickets(int ticketid, int agentId) throws Exception {
	Ticket ticket= ticketDao.findById(ticketid).orElse(null);
	Agent agent=agentRespo.findById(agentId).orElse(null);
	if(ticket.getCategoryId().getCategoryId()==agent.getCategory().getCategoryId()) {
		if(ticket.getStatus().equals("Handled")) {
			ticket.setStatus("Closed");
			ticket.setClosedDate(new Date());
			ticketDao.save(ticket);
		}
		else {
			throw new Exception("This Ticket Was Not Being Handled");
		}
	}
	
	
}

@Override
public List<Ticket> getPrority() {
	// TODO Auto-generated method stub
	return null;
}

	
//	@Override
//	public void assignToAgents(int ticketId, int agentId) {
//		Ticket ticket = ticketDao.findByticketId(ticketId);
////		if (ticket == null) {
////			System.out.println("No tickets Is found for that Particular Details");
////		}
//		Integer tickCategory = ticket.getCategoryId().getCategoryId();
//		System.out.println(tickCategory);
//		Agent agent = agentRespo.findById(agentId).orElse(null);
////		to find category of that Agents
////		if (agent == null) {
////			System.out.println("Invalid Agent Id");
////		}
//		Integer agentCategory = agent.getCategory().getCategoryId();		
//		if(tickCategory==agentCategory) {
//			System.out.println("Same");
//		}
//		else {
//			System.out.println("Wrong");
//		}
//
////		Category ticketCategory = ticket.getCategoryId();
//
////		if (.contains(agent)) {
////            return ResponseEntity.badRequest().body("Agent does not belong to the ticket category.");
////        }
//
//	}

}
