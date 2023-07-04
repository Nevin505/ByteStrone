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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.annotation.PutExchange;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Response;
import com.bytes.train.entities.Ticket;
import com.bytes.train.service.AgentCategoryService;

@RestController
@RequestMapping("/agents")
@CrossOrigin("http://localhost:4200")
public class AgentController {

	@Autowired
	AgentCategoryService agentService;

	// To Check Agent Access for login
	@PostMapping("/Access")
	public ResponseEntity<Response> checkLogin(@RequestBody Agent agent) {
		try {
			String userName = agent.getAgentName();
			String password = agent.getAgentPassword();
			Agent response = agentService.checkAccess(userName, password);
			if (response == null) {
				return ResponseEntity.ok(new Response("Invalid Login", null, false));
			}
			return ResponseEntity.ok(new Response("Login Successfull", response, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("Server Error", null, false));
		}
	}

	// To Get list of Open Tickets
	@GetMapping("/tickets")
	public ResponseEntity<Response> getOpenTickets() {
		try {
			List<Ticket> ticket = agentService.findOpen();
			if (ticket == null) {
				return ResponseEntity.ok(new Response("No Open Tickets Are There", ticket, false));
			} else {
				return ResponseEntity.ok(new Response("The Open Tickets Are There", ticket, true));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(" Error", null, false));
		}
	}

	// To Search By Using Ticket Id For Manager
	@GetMapping("/specificticket/{ticketid}")
	public ResponseEntity<Response> getSpecificTicket(@PathVariable int ticketid) {
		try {
			Ticket ticket = agentService.getSpecificTicketId(ticketid);
			if (agentService.getSpecificTicketId(ticketid) == null) {
				return ResponseEntity.ok(new Response("No Ticket With The Particular Is Found", null, false));
			} else {
				return ResponseEntity.ok(new Response("The Details Of The Ticket With Oarticular Id is", ticket, true));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("Error", null, false));
		}
	}

	// To get the list of Tickets Which belongs to particular Categories
	@GetMapping("/categorytickets/{agentId}")
	public ResponseEntity<List<Ticket>> getParticularAgentTickets(@PathVariable int agentId) {
		return ResponseEntity.ok(agentService.getParticularCategoryList(agentId));

	}

	// To Search Tickets Based On Categories
	@GetMapping("/categorytickets/{agentId}/{ticketId}")
	public ResponseEntity<Response> searchTicketCategories(@PathVariable int agentId, @PathVariable int ticketId) {
		Ticket ticket = agentService.searchParticularCategoryList(agentId, ticketId);
		if (ticket == null) {
			return ResponseEntity.ok(new Response("Not Found", null, false));
		}
		return ResponseEntity.ok(new Response("Search SucessFull", ticket, true));
	}

	// To assign Agents Based on category with no ticket Payload Basis
	@GetMapping("/{ticketid}/assign/{agent}")
	public ResponseEntity<String> ticketsssignment(@PathVariable int ticketid, @PathVariable int agent) {
		try {
			String res = agentService.assignToAgents(ticketid, agent);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

//	To Close a Ticket which has been handled by a particular Agent
	@PutMapping("/{ticketid}/closedticket/{agentId}")
	public ResponseEntity<String> closeAsignedTickets(@PathVariable int ticketid, @PathVariable int agentId) {
		try {
			
			return ResponseEntity.ok(agentService.closeTickets(ticketid, agentId));

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/assignedTickets/{agentId}")
	public ResponseEntity<List<Ticket>>  ticketAssigned(@PathVariable int agentId) {
		return ResponseEntity.ok(agentService.getAssignedTickets(agentId));
	}

	// To Get The List Of Agent
	@GetMapping("/agentsCategory/{agentId}")
	public ResponseEntity<List<Agent>> agents(@PathVariable int agentId) {
		return ResponseEntity.ok(agentService.getAgents(agentId));

	}

	@GetMapping("/information")
	public List<Agent> getDetailsAgent() {
		return agentService.getFullDetais();
	}

	@PostMapping("/{agentId}/categories/{categoryId}")
	public void addCategoryToAgent(@PathVariable int agentId, @PathVariable int categoryId) {
		agentService.addCategoryToAgent(agentId, categoryId);
	}

	@DeleteMapping("/{agentId}/categories/{categoryId}")
	public void removeCategoryFromAgent(@PathVariable int agentId, @PathVariable int categoryId) {
		agentService.removeCategoryFromAgent(agentId, categoryId);
	}

	@GetMapping("/getfullagents")
	public List<Agent> getInfo() {
		return agentService.getFullInfo();
	}
}
