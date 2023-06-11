package com.bytes.train.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Comment;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.service.AgentService;

@RestController
@RequestMapping("/agents")
@CrossOrigin("http://localhost:4200")
public class AgentController {
	@Autowired
	AgentService agentService;

//	To Check Agent Access for login
	@PostMapping("/Access")
	public ResponseEntity<Agent> checkAccess(@RequestBody Agent agent) {
		String userName = agent.getAgentName();
		String password = agent.getAgentPassword();
		return agentService.checkAccess(userName, password);
	}

//To Get list of Open Tickets
	@GetMapping("/tickets")
	public List<Ticket> getOpenTickets() {
		return agentService.findOpen();
	}
	
//	to Search By Using  Ticket Id 
	@GetMapping("/specificticket/{ticketid}")
	public ResponseEntity<Ticket> getSpecificTicket(@PathVariable int ticketid) throws Exception {
		 if(agentService.getSpecificTicketId(ticketid)==null) {
			 throw new Exception("Ticket With That Id Is Not Found");
		 }
		return ResponseEntity.ok((agentService.getSpecificTicketId(ticketid)));
	}


//To get the list of Tickets Which belongs to  particular Categories
	@GetMapping("/categorytickets/{agentId}")
	public List<Ticket> getParticularAgentTickets(@PathVariable int agentId) {
		return agentService.getParticularCategoryList(agentId);

	}
	
//	to Assign A particular ticket to a agents which belongs to that agent category which has a ticket payload
	@PostMapping("/{ticketid}/assignlimit/{agentId}")
	public ResponseEntity<String> assignAgents(@PathVariable int ticketid, @PathVariable int agentId) {
		agentService.assignTicketsLimit(ticketid, agentId);
		return ResponseEntity.ok("Ticket Assigned Succesfully");

	}
// To assign Agents Based on category with no ticket Payload Basis
	@PostMapping("/{ticketid}/assign/{agent}")
	public void ticketsssignment(@PathVariable int ticketid, @PathVariable int agent) throws Exception {
		try {
			agentService.assignToAgents(ticketid, agent);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

//	To Close a Ticket which has been handled by a particular Agent
	@PostMapping("/{ticketid}/closedticket/{agentId}")
	public ResponseEntity<String> closeAsignedTickets(@PathVariable int ticketid, @PathVariable int agentId) {
		try {
			agentService.closeTickets(ticketid, agentId);
			return ResponseEntity.ok("Ticket Closed");

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/agentsCategory/{agentId}")
	public List<Agent> agents(@PathVariable int agentId){
		return agentService.getAgents(agentId);
		
	}
	 @GetMapping("/addComments")
	public void addComments(int ticketId,@RequestBody Comment comment) {
		 agentService.addCommentService(ticketId,comment);
	}
}
