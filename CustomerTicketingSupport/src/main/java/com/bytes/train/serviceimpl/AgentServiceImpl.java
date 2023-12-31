package com.bytes.train.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Comment;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CategoryRespository;
import com.bytes.train.repos.CommentRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService {
	@Autowired
	TicketRepository ticketDao;

	@Autowired
	AgentRepository agentRespo;

	@Autowired
	CategoryRespository categoryRespository;

	@Autowired
	CommentRepository commentRepoistory;

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

//	Assign Tickets To Agent Based On payLoad
	@Override
	public void assignTicketsLimit(int ticketId, int agentId) {
		Ticket ticket = ticketDao.findByticketId(ticketId);
		Agent agent = agentRespo.findById(agentId).orElse(null);
		int maxWorkload = 22;
		if (ticket.getAgentId() == null && agent != null) {
			if (ticket.getCategoryId().getCategoryId() == agent.getCategory().getCategoryId()) {
				if (getWorkloadForAgent(agentId) < maxWorkload) {
					ticket.setAgentId(agent);
					ticket.setStatus("Handled");
					ticketDao.save(ticket);
				} else {
					throw new IllegalStateException(
							"Agent's workload is already at the maximum limit.Assigin It other Agents");
				}
			}

			else {
				throw new IllegalStateException("No Agents were found for the particular Category");
			}

//		} else if (ticket.getAgentId().getAgentID() == agentId && ticket.getStatus().equals("Handled")) {
//			ticket.setClosedDate(new Date());
//			ticket.setStatus("Closed");
//			ticketDao.save(ticket);
		} else {
			throw new IllegalArgumentException("Ticket or agent not found.");
		}
	}

	
//
	public int getWorkloadForAgent(int agentId) {
		return ticketDao.getCountByAgentId(agentId);
	}

//Assigin to that Agent
	@Override
	public void assignToAgents(int ticketId, int agentId) throws Exception {

		Ticket ticket = ticketDao.findById(ticketId).orElse(null);
		Ticket ticket2 = ticketDao.findById(ticketId).orElse(null);
		if (ticket == null) {
			throw new Exception("No tickets found for the specified details");
		}
		Agent agent = agentRespo.findById(agentId).orElse(null);
		if (agent == null) {
			throw new Exception("No Agents were found ");
		}
		if (ticket.getCategoryId().getCategoryId() == agent.getCategory().getCategoryId()) {
			if (ticket.getAgentId() == null) {
				ticket.setAgentId(agent);
				ticket.setStatus("Handled");
				ticketDao.save(ticket);
			} else {
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
		Ticket ticket = ticketDao.findById(ticketid).orElse(null);
		if (ticket.getAgentId().getAgentID() == agentId && (ticket.getStatus().equals("Handled"))) {
			ticket.setStatus("Closed");
			ticket.closeTicket();
			ticketDao.save(ticket);
		} else {
			throw new Exception("This Ticket Was Not Being Closed By That Agent Who Has Handled");
		}
	}

	@Override
	public List<Agent> getAgents(int id) {
		Agent agent = agentRespo.findById(id).orElse(null);
		Category category = agent.getCategory();
		return agentRespo.findByCategory(category);

	}

	@Override
	public void addCommentService(int ticketId, Comment comment) {
//       	Comment comment	

//		String comment2=comment.getContent();
		commentRepoistory.save(comment);

	}

}
