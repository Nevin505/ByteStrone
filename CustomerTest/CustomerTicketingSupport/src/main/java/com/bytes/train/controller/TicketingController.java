package com.bytes.train.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Response;
import com.bytes.train.entities.Skill;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.SkillRepository;
import com.bytes.train.service.TicketService;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("http://localhost:4200")
public class TicketingController {

	@Autowired
	TicketService ticketService;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	AgentRepository agentRepository;

	@GetMapping("/query")
	public List<Ticket> getTicketInfo() {
		return ticketService.getTicket();
	}

	@PostMapping("/addticket/{id}")
	public ResponseEntity<Response> saveTicketByPostman(@PathVariable int id, @RequestBody Ticket ticket) {
		try {
			System.out.println(ticket);
			Ticket newTicket = ticketService.saveTicketPostman(id, ticket);
			return ResponseEntity.ok(new Response("The Ticket Was Being Added", newTicket, true));

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}

	}

	@GetMapping("/getTicket/{ticketId}")
	public ResponseEntity<Response> getTicket(@PathVariable int ticketId) {
		try {
			Ticket singleTicket = ticketService.getCustomerSingleTicket(ticketId);
			return ResponseEntity.ok(new Response("The Ticket is", singleTicket, true));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}

	}

	@GetMapping("/values")
	public ResponseEntity<Response> getMapping() {
		try {
			List<Category> category = ticketService.optionsvalue();
			System.out.println(category);
			return ResponseEntity.ok(new Response("The Tickets Category Are", category, true));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}

	}

	@PostMapping("/addSkills/{agentId}/{skillId}")
	public void addSkills(@PathVariable int agentId, @PathVariable int skillId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		Skill skill = skillRepository.findById(skillId).orElseThrow();
		List<Skill> agentSkills = agent.getSkills();
		agentSkills.add(skill);
		List<Agent> skillAgents = skill.getAgentIds();
		skillAgents.add(agent);
		agentRepository.save(agent);
		skillRepository.save(skill);

	}

	@DeleteMapping("/removeSkills/{agentId}/{skillId}")
	public void removeCategoryFromAgent(@PathVariable int agentId, @PathVariable int skillId) {
		Agent agent = agentRepository.findById(agentId).orElseThrow();
		Skill skill = skillRepository.findById(skillId).orElseThrow();
		agent.getSkills().remove(skill);
		skill.getAgentIds().remove(agent);
		agentRepository.save(agent);
		skillRepository.save(skill);
	}
 
	@PostMapping("/assgination/{ticketId}")
	public ResponseEntity<Response> assiginationTickets(@PathVariable int ticketId) {
		try {
			return ResponseEntity.ok(
					new Response("The Ticket is being Assigned", ticketService.assiginationTickets(ticketId), true));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response(e.getMessage(), null, false));
		}
	}

}
