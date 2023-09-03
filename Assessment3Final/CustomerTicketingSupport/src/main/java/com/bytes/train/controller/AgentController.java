package com.bytes.train.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.FilterCreteria;
import com.bytes.train.entities.Response;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.service.AgentCategoryService;

@RestController
@RequestMapping("/agents")
@CrossOrigin("http://localhost:4200")
public class AgentController {

	@Autowired
	private AgentCategoryService agentService;

	@Autowired
	private AgentRepository agentRepository;

	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);

// To get the list of Tickets Which belongs to Agents Categories
	@GetMapping("/categorytickets/{agentId}")
	public ResponseEntity<Response> getParticularAgentTickets(@PathVariable int agentId) throws Exception {
		try {
			List<Ticket> agentsTickets = agentService.getParticularCategoryList(agentId);
			return ResponseEntity.ok(new Response("The Agents Category Tickets Are", agentsTickets, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Issue Is", e); 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));

		}
	}
	
//	Filters Open and Closed in a single Function
	@PostMapping("/getfilteredtickets/{agentId}")
	public ResponseEntity<Response> getfilteredtickets(@PathVariable("agentId") int agentId,@RequestBody FilterCreteria filterCreteria) {
		try {
			String status=filterCreteria.getStatus();
			List<Ticket> filterTickets = agentService.getfilteredTickets(agentId,status);
			return ResponseEntity.ok(new Response("The Filtered Tickets Are", filterTickets, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}
	}	

//	Search Tickets Based on  Number and Subject
	@PostMapping("/search/{agentId}")
	public ResponseEntity<Response> searchTickets(@PathVariable("agentId") int agentId,
			@RequestBody SearchCriteria searchCriteria) {
		try {
			List<Ticket> searchTickets = agentService.getSearch(agentId, searchCriteria);
			return ResponseEntity.ok(new Response("The Search Result Is", searchTickets, true));
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((new Response(e.getMessage(), null, false)));
		}
	}

	// To Get The List Of Agent Which belongs To a
	@GetMapping("/agentsCategory/{agentId}")
	public ResponseEntity<Response> agents(@PathVariable int agentId) {
		try {
			List<Agent> agentList = agentService.getAgents(agentId);
			return ResponseEntity.ok(new Response("The Agents List Are", agentList, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}

	}

// To assign Agents Based on category 
	@PutMapping("/{ticketid}/assign/{agent}")
	public ResponseEntity<Response> ticketsssignment(@PathVariable int ticketid, @PathVariable int agent) {
		try {
			String res = agentService.assignToAgents(ticketid, agent);
			return ResponseEntity.ok(new Response(res, null, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}   
	}

//	To Close a Ticket which has been handled by a particular Agent
	@PutMapping("/{ticketid}/closedticket/{agentId}")
	public ResponseEntity<Response> closeAsignedTickets(@PathVariable int ticketid, @PathVariable int agentId) {
		System.out.println(agentId);
		try {
			Ticket ticket = agentService.closeTickets(ticketid, agentId);
			if (ticket == null) {
				return ResponseEntity.ok(new Response("Ticket is Not Being Assigined", null, false));
			}
			return ResponseEntity.ok(new Response("Ticket is  Being Closed ", ticket, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}
	}

//	To View Assigned Tickets by Agents
	@GetMapping("/assignedTickets/{agentId}")
	public ResponseEntity<Response> ticketAssigned(@PathVariable int agentId) {
		try {
			List<Ticket> agentTicket = agentService.getAssignedTickets(agentId);
			return ResponseEntity.ok(new Response("The Assigined Tickets Are", agentTicket, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}
	}

	// To Assign By Ticket fOR Agents
	@GetMapping("/specificticket/{ticketid}")
	public ResponseEntity<Response> getSpecificTicket(@PathVariable int ticketid) {
		try {
			Ticket ticket = agentService.getSpecificTicketId(ticketid);
			return ResponseEntity.ok(new Response("The Ticket Details", ticket, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
		} catch (Exception e) {
			logger.error("The Errors Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}
	} 
//	
////	Regarding Many to Many RelationShip
//	@GetMapping("/information")
//	public List<Agent> getDetailsAgent() {
//		return agentService.getFullDetais();
//	}
//
//	@PostMapping("/agents/{agentId}/categories/{categoryId}")
//	public void addCategoryToAgent(@PathVariable int agentId, @PathVariable int categoryId) {
//		agentService.addCategoryToAgent(agentId, categoryId);
//	}
//
//	@DeleteMapping("/{agentId}/categories/{categoryId}")
//	public void removeCategoryFromAgent(@PathVariable int agentId, @PathVariable int categoryId) {
//		agentService.removeCategoryFromAgent(agentId, categoryId);
//	}
//
//	@GetMapping("/getfullagents")
//	public List<Agent> getInfo() {
//		return agentService.getFullInfo();
//	}
//
//	@GetMapping("/searchtickets/{agentId}")
//	public List<Ticket> getTicketsForAgent(@PathVariable int agentId, @RequestBody SearchCriteria request) {
//		return agentService.testService(agentId, request);
//	}
//
//	@PostMapping("/add")
//	public Agent createAgents(@RequestBody Agent agent) {
//		return agentRepository.save(agent);
//
//	}
//
//	@GetMapping("/adds/{id}")
//	public Agent createAgentss(@PathVariable int id) {
//		return agentRepository.findById(id).orElseThrow();
//
//	}
//	
}
