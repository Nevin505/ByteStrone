package com.bytes.train.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CategoryRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.AgentCategoryService;

import jakarta.persistence.EntityNotFoundException;

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

	@Override
	public List<Ticket> findOpen() throws Exception {
		List<Ticket> ticket = ticketRepository.findOpenTickets();
		if (ticket == null) {
			throw new Exception("There are no open Tickets");
		}
		return ticketRepository.findOpenTickets();
	}

	// To find a Ticket By Specific Ticket Id
	@Override
	public Ticket getSpecificTicketId(int id) {
		Ticket ticket = ticketRepository.findById(id).orElse(null);
//			if(ticket==null) {
//				throw new Exception("\"Ticket With That Id Is Not Found");
//			}
		return ticket;
	}

	// To get the list of Tickets Which belongs to particular Categories
	@Override
	public List<Ticket> getParticularCategoryList(int agentId) {
		Agent agent = agentRepository.findById(agentId).orElse(null);
		List<Ticket> tickets = ticketRepository.findAll();
		System.out.println(tickets);
		List<Category> category = agent.getCategory();
		Set<Ticket> tickets2 = new HashSet();
		int var1;
		for (Category category2 : category) {
			var1 = category2.getCategoryId();
			for (Ticket ticket : tickets) {
				if (ticket.getCategoryId().getCategoryId() == var1) {
					tickets2.add(ticket);
				}
			}

		}

		return new LinkedList<>(tickets2);
	}

	@Override
	public Ticket searchParticularCategoryList(int agentId, int ticketId) {
		Agent agent = agentRepository.findById(agentId).orElse(null);
		List<Category> category = agent.getCategory();
		int categoryId;
		for (Category category2 : category) {
			categoryId = category2.getCategoryId();
			List<Ticket> ticket = ticketRepository.findByCategoryId_CategoryId(categoryId);
			for (Ticket ticket1 : ticket) {
				if (ticket1.getTicketId() == ticketId) {
					return ticket1;
				}
			}
		}
		return null;

	}

	@Override
	public String assignToAgents(int ticketId, int agentId) {

		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		Agent agent = agentRepository.findById(agentId).orElse(null);

		List<Category> agent1 = agent.getCategory();

		for (Category category : agent1) {
			System.out.println(ticket.getCategoryId().getCategoryId());
			System.out.println(category.getCategoryId());
			if (ticket.getCategoryId().getCategoryId() == category.getCategoryId()) {
				if (ticket.getAgentId() == null) {
					ticket.setAgentId(agent);
					ticket.setStatus("Assigined");
					ticketRepository.save(ticket);
					return "The Ticket Is Being Assigined";
				}

			}

		}
		return " Tickets Is Already Registered";

	}

	@Override
	public List<Ticket> getAssignedTickets(int agentId) {
		List<Ticket> tickets = ticketRepository.findAssiginedTickets();
		List<Ticket> getAssigined = new LinkedList<>();
		for (Ticket ticket : tickets) {
			if (ticket.getAgentId().getAgentID() == agentId) {
				getAssigined.add(ticket);
			}
		}
		return getAssigined;
	}


//	/To close The Tickets Which has Been Handled By That Agent
	@Override
	public String closeTickets(int ticketid, int agentId) throws Exception {
		Ticket ticket = ticketRepository.findById(ticketid).orElse(null);
		if (ticket.getAgentId().getAgentID() == agentId && (ticket.getStatus().equals("Assigined"))) {
			ticket.setStatus("Closed");
			ticket.closeTicket();
			ticketRepository.save(ticket);
			return "Ticket Has Been Closed";
		} else {
//			throw new Exception("This Ticket Was Not Being Closed By That Agent Who Has Handled");
			return  "Error In closing The Tickets";
		}
	}

	@Override
	public List<Agent> getAgents(int id) {
		Agent agent = agentRepository.findById(id).orElse(null);
		List<Category> categ = agent.getCategory();

		List<Agent> agentList = new LinkedList<>();

		List<Agent> allAgents = agentRepository.findAll();

		for (Agent otherAgent : allAgents) {
			List<Category> allAgentsCat = otherAgent.getCategory();
			if ((otherAgent.equals(agent)) || allAgentsCat.containsAll(categ)
					|| !Collections.disjoint(allAgentsCat, categ)) {
				agentList.add(otherAgent);
			}

		}
		return agentList;

	}

	@Override
	public List<Agent> getFullDetais() {

		return agentRepository.findAll();
	}

	public void addCategoryToAgent(int agentId, int categoryId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow(EntityNotFoundException::new);
		Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
		agent.getCategory().add(category);
		category.getAgentId().add(agent);
		agentRepository.save(agent);
		categoryRepository.save(category);
	}

	public void removeCategoryFromAgent(int agentId, int categoryId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow(EntityNotFoundException::new);
		Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
		agent.getCategory().remove(category);
		category.getAgentId().remove(agent);
		agentRepository.save(agent);
		categoryRepository.save(category);
	}

	@Override
	public Agent checkAccess(String userName, String password) {
		Agent userdata = agentRepository.findByagentName(userName);
		if ((userdata.getAgentName().equals(userName)) && (userdata.getAgentPassword().equals(password))) {
			return userdata;
		}
		return null;
	}

}
