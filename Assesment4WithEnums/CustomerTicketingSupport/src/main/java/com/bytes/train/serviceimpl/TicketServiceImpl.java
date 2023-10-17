package com.bytes.train.serviceimpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Skill;
import com.bytes.train.entities.StatusEnum;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CategoryRepository;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.SkillRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AgentRepository agentRespo;

	@Autowired
	private CategoryRepository categoryRespository;

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public List<Ticket> getTicket() {
		return ticketRepository.findAll();
	}

	// Via Postman
	@Override
	public Ticket saveTicketPostman(int id, Ticket ticket) throws Exception {
		Customer customer = customerRepository.findById(id).orElse(null);
		if (customer == null) {
			throw new ResourceNotFoundException("No Customer Is Found");
		}
		Category category = categoryRespository.findByCategoryName(ticket.getCategoryName());
		if (ticket.getPriority().trim().length() == 0 || ticket.getSubject().trim().length() == 0 || ticket.getDescription().trim().length() == 0) {
			throw new Exception("Please Enter Details");
		}
		ticket.setCategoryId(category);
		ticket.setCustomer(customer);
		System.out.println(ticket);
		return ticketRepository.save(ticket);
		
	}
   
	@Override
	public List<Category> optionsvalue() {
		return categoryRespository.findAll();
	}

	@Override
	public Ticket getCustomerSingleTicket(int ticketId) {
		return ticketRepository.findById(ticketId).orElse(null);
	}

	@Override
	public String assiginationTickets(int ticketId) throws Exception {
		Ticket ticket = ticketRepository.findByticketId(ticketId);
		if (ticket == null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		String ticketPriority = ticket.getPriority();
		Category category = ticket.getCategoryId();
		String skillname = category.getCategory_name();
		System.out.println("The Skill"+skillname);
		Skill allSkills = skillRepository.findBySkillName(skillname);
//		System.out.println(allSkills);
		
		if (((StatusEnum.Open).equals(ticket.getStatus())) && ticketPriority.equals("High") && ticket.getAgentId() == null) {
			System.out.println("Here Open High");
			List<Agent> agentsWithSameSkills = agentRespo.findBySkillsInAndActive(Collections.singletonList(allSkills),
					"Yes");
//			Collections.sort(agentsWithSameSkills);
			int countThreshold = 4;
			Agent selectedAgent = agentsWithSameSkills.get(0); 
			System.out.println(agentsWithSameSkills);
			for (Agent agent : agentsWithSameSkills) {
				if (agent.getAgentCount() < countThreshold && agent.getExperiance() > 3
						&& agent.getAgentCount() <= selectedAgent.getAgentCount()) {
					selectedAgent = agent;
				}
			}
			if (selectedAgent != null) {
				ticket.setAgentId(selectedAgent);
				ticket.setStatus(StatusEnum.Assigned);
				int count = selectedAgent.getAgentCount();
				selectedAgent.setAgentCount(count + 1);
				agentRespo.save(selectedAgent);
				ticketRepository.save(ticket);
				return  "The Ticket Was Assigined "+ticket.getAgentId().getName();
			}
			return "The Ticket Was Not Being Assigined";

		} else if ((ticket.getPriority().equals("Medium") || ticket.getPriority().equals("Low"))
				&& ticket.getAgentId() == null && ticket.getStatus().equals("Open")) {
			System.out.println("Medium Started");
			List<Agent> agentsWithSameSkills = agentRespo.findBySkillsInAndActive(Collections.singletonList(allSkills),
					"Yes");
//			Collections.sort(agentsWithSameSkills);
			int countThreshold = 6;
			System.out.println("Medium");
			System.out.println(agentsWithSameSkills);
			Agent selectedAgent = agentsWithSameSkills.get(0);
			for (Agent agent : agentsWithSameSkills) {
				if (agent.getAgentCount() < countThreshold && (agent.getExperiance() < 4 || agent.getExperiance() > 0)
						&& agent.getAgentCount() <= selectedAgent.getAgentCount()) {
					selectedAgent = agent;
				}
			}
			if (selectedAgent == null) {
				for (Agent agent : agentsWithSameSkills) {
					if (agent.getAgentCount() < countThreshold && agent.getExperiance() > 4
							&& agent.getAgentCount() <= selectedAgent.getAgentCount()) {
						selectedAgent = agent;
					}
				}
			}
			if (selectedAgent != null) {
				ticket.setAgentId(selectedAgent);
				ticket.setStatus(StatusEnum.Assigned);
				int count = selectedAgent.getAgentCount();
				selectedAgent.setAgentCount(count + 1);
				agentRespo.save(selectedAgent);
				ticketRepository.save(ticket);
				return "The Ticket Was Assigined "+ticket.getAgentId().getName();
			}
			return "The Ticket Was Not Assigined";
		} else {
			return "The Ticket Is Not Opened";
		}
	}

}
