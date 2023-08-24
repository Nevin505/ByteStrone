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
	TicketRepository ticketRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AgentRepository agentRespo;

	@Autowired
	CategoryRepository categoryRespository;

	@Autowired
	SkillRepository skillRepository;

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
		if (ticket.getPriority() == null || ticket.getSubject() == null || ticket.getDescription() == null) {
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
	public Ticket assiginationTickets(int ticketId) throws ResourceNotFoundException {
		Ticket ticket = ticketRepository.findByticketId(ticketId);
		if (ticket == null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		String ticketPriority = ticket.getPriority();
		Category category = ticket.getCategoryId();
		String skillname = category.getCategory_name();
		System.out.println(skillname);
		Skill allSkills = skillRepository.findBySkillName(skillname);
		System.out.println(allSkills);
		if (ticket.getStatus().equals("Open") && ticketPriority.equals("High") && ticket.getAgentId() == null) {
			System.out.println("Here Open High");
			List<Agent> agentsWithSameSkills = agentRespo.findBySkillsInAndActive(Collections.singletonList(allSkills),
					"Yes");
			Collections.sort(agentsWithSameSkills, new AgentExperienceComparator());
			System.out.println("With Experiance");
			System.out.println(agentsWithSameSkills);

			System.out.println("With Payload");
			System.out.println(agentsWithSameSkills);
			int countThreshold = 4;
			Agent selectedAgent = agentsWithSameSkills.get(0);
			System.out.println(agentsWithSameSkills);
			for (Agent agent : agentsWithSameSkills) {
				if (agent.getAgentCount() < countThreshold && agent.getExperiance()>3 && agent.getAgentCount()<=selectedAgent.getAgentCount()) {
						selectedAgent = agent;
						System.out.println(agent.getName());
						System.out.println(selectedAgent.getName());

				}
			}
			if (selectedAgent != null) {
				ticket.setAgentId(selectedAgent);
				ticket.setStatus("Assigned");
				int count = selectedAgent.getAgentCount();
				selectedAgent.setAgentCount(count + 1);
				agentRespo.save(selectedAgent);
				System.out.println(selectedAgent.getAgentCount());
				ticketRepository.save(ticket);
				return ticket;
			}
			return null;

		} else if ((ticket.getPriority().equals("Medium") || ticket.getPriority().equals("Low")) && ticket.getAgentId() == null){
			System.out.println("Medium Started");
			List<Agent> agentsWithSameSkills = agentRespo.findBySkillsInAndActive(Collections.singletonList(allSkills),"Yes");
			Collections.sort(agentsWithSameSkills, Collections.reverseOrder(new AgentExperienceComparator()));
			System.out.println(agentsWithSameSkills);
			int countThreshold = 6;
			System.out.println("Medium");
			System.out.println(agentsWithSameSkills);
			Agent selectedAgent = agentsWithSameSkills.get(0);
			for (Agent agent : agentsWithSameSkills) {
				if (agent.getAgentCount() < countThreshold &&( agent.getExperiance()<4 || agent.getExperiance()>0)&& agent.getAgentCount()<=selectedAgent.getAgentCount()) {
					selectedAgent = agent;
				}
			}
			if(selectedAgent==null) {
				for (Agent agent : agentsWithSameSkills) {
					if (agent.getAgentCount() < countThreshold &&agent.getExperiance()>4 && agent.getAgentCount()<=selectedAgent.getAgentCount()) {
						selectedAgent = agent;
					}
				}
			}
			if (selectedAgent != null) {
				ticket.setAgentId(selectedAgent);
				ticket.setStatus("Assigned");
				int count = selectedAgent.getAgentCount();
				selectedAgent.setAgentCount(count + 1);
				agentRespo.save(selectedAgent);
				System.out.println(selectedAgent.getAgentCount());
				ticketRepository.save(ticket);
				return ticket;
			}
	
			return  null;

		} else {
			return null;
		}

	}

	private static class AgentExperienceComparator implements Comparator<Agent> {
		public int compare(Agent agent1, Agent agent2) {
			return Integer.compare(agent2.getExperiance(), agent1.getExperiance());
		}
	}
	private static class AgentPayloadComparator implements Comparator<Agent> {
		public int compare(Agent agent1, Agent agent2) {
			return Integer.compare(agent2.getAgentCount(), agent1.getAgentCount());
		}
	}


}

//for (Agent agent : agentsWithSameSkills) {
//if (agent.getAgentCount() < countThreshold) {
//	if (selectedAgent == null || agent.getAgentCount() > selectedAgent.getAgentCount()) {
//		selectedAgent = agent;
//	}
//}
//}
//if (ticketPriority.equals("High")) {
//
//}
//System.out.println("Hai");
//System.out.println(selectedAgent);
//System.out.println(agentsWithSameSkills);
//return agentsWithSameSkills;
