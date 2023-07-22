package com.bytes.train.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bytes.train.entities.Response;
import com.bytes.train.entities.SearchCriteria;
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
			return ResponseEntity.ok(new Response("Invalid Login", null, false));
		}
	}

// To get the list of Tickets Which belongs to Agents Categories
	@GetMapping("/categorytickets/{agentId}")
	public ResponseEntity<Response> getParticularAgentTickets(@PathVariable int agentId) {
		try {
			List<Ticket> agentsTickets = agentService.getParticularCategoryList(agentId);
			return ResponseEntity.ok(new Response("The Agents Category Tickets Are", agentsTickets, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("There Exist No Such Agent Id", null, false));
		}

	}

//	Filters Open and Closed Tickets
	// To Get Open Tickets Filter
	@GetMapping("/getOpentickets/{agentId}")
	public ResponseEntity<Response> getOpentickets(@PathVariable("agentId") int agentId) {
		try {
			List<Ticket> openTickets = agentService.getticketsOpen(agentId);
			return ResponseEntity.ok(new Response("The Open Tickets Are", openTickets, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("There Exist No Such Agent Id", null, false));
		}
	}

//To Get Closed Tickets Filter
	@GetMapping("/getCloseTickets/{agentId}")
	public ResponseEntity<Response> closeTickets(@PathVariable("agentId") int agentId) {
		try {
			List<Ticket> closedTickets = agentService.getclosedTickets(agentId);
			return ResponseEntity.ok(new Response("The Closed Tickets Are", closedTickets, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("There Exist No Such Agent Id", null, false));
		}

	}

//	Search Tickets Based on  Number and Subject
	@PostMapping("/search/{agentId}")
	public ResponseEntity<Response> searchTickets(@PathVariable("agentId") int agentId,
			@RequestBody SearchCriteria searchCriteria) {
		try {
			List<Ticket> searchTickets = agentService.getSearch(agentId, searchCriteria);
			return ResponseEntity.ok(new Response("The Search Result Is", searchTickets, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("Error", null, false));
		}
	}

	// To Get The List Of Agent Which belongs To a Categogry
	@GetMapping("/agentsCategory/{agentId}")
	public ResponseEntity<Response> agents(@PathVariable int agentId) {
		try {
			List<Agent> agentList = agentService.getAgents(agentId);
			return ResponseEntity.ok(new Response("The Agents List Are", agentList, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}

	}

// To assign Agents Based on category 
	@PutMapping("/{ticketid}/assign/{agent}")
	public ResponseEntity<Response> ticketsssignment(@PathVariable int ticketid, @PathVariable int agent) {
		try {
			String res = agentService.assignToAgents(ticketid, agent);
			return ResponseEntity.ok(new Response(res, null, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}
	}

//	To Close a Ticket which has been handled by a particular Agent
	@PutMapping("/{ticketid}/closedticket/{agentId}")
	public ResponseEntity<Response> closeAsignedTickets(@PathVariable int ticketid, @PathVariable int agentId) {
		try {
			Ticket ticket = agentService.closeTickets(ticketid, agentId);
			if (ticket == null) {
				return ResponseEntity.ok(new Response("Ticket is Not Being Assigined", null, false));
			}
			return ResponseEntity.ok(new Response("Ticket is  Being Closed ", ticket, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("No Ticket Is Found", null, false));
		}
	}

//	To View Assigined Tickets bY Agents
	@GetMapping("/assignedTickets/{agentId}")
	public ResponseEntity<Response> ticketAssigned(@PathVariable int agentId) {
		try {
			List<Ticket> agentTicket = agentService.getAssignedTickets(agentId);
			return ResponseEntity.ok(new Response("The Assigined Tickets Are", agentTicket, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}
	}

	// To Assigin By Ticket fOR Agents
	@GetMapping("/specificticket/{ticketid}")
	public ResponseEntity<Response> getSpecificTicket(@PathVariable int ticketid) {
		try {
			Ticket ticket = agentService.getSpecificTicketId(ticketid);
			return ResponseEntity.ok(new Response("The Ticket Details", ticket, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}
	}

	
//	Regarding Many to Many RelationShip
	@GetMapping("/information")
	public List<Agent> getDetailsAgent() {
		return agentService.getFullDetais();
	}

	@PostMapping("/agents/{agentId}/categories/{categoryId}")
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

	@GetMapping("/searchtickets/{agentId}")
	public List<Ticket> getTicketsForAgent(@PathVariable int agentId, @RequestBody SearchCriteria request) {
		return agentService.testService(agentId, request);
	}

}
