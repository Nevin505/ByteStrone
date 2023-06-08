package com.bytes.train.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.service.AgentService;

//As a support agent, I want to view and manage support tickets efficiently so that I can prioritise and resolve them in a timely manner.
//
//Acceptance Criteria:
//
//Support agents should have a dashboard displaying all open tickets, sorted by priority or status.
//Agents should be able to assign tickets to themselves or other team members.
//The system should provide filters and search functionality to locate specific tickets quickly.

@RestController
@RequestMapping("/agents")
@CrossOrigin("http://localhost:4200")
public class AgentController {
	@Autowired
	AgentService agentService;

	
//	To Check Agent Access
	@PostMapping("/Access")
	public ResponseEntity<Agent> checkAccess(@RequestBody Agent agent) {
		String userName = agent.getAgentName();
		String password = agent.getAgentPassword();
		return agentService.checkAccess(userName, password);
	}
	

//To Get all the Open Tickets
	@GetMapping("/tickets")
	public List<Ticket> getOpenTickets() {
		return agentService.findOpen();
	}

//
	@GetMapping("/categorytickets/{agentId}")
	public List<Ticket> getParticularAgentTickets( @PathVariable int agentId){
		return agentService.getParticularCategoryList(agentId);
		
	}
	
	
//	to Search By Using 


	@GetMapping("/specificticket/{ticketid}")
	public Ticket getSpecificTicket(@PathVariable int ticketid) {
		return agentService.getSpecificTicketId(ticketid);

	}
	
	@GetMapping("/categoryagents")
	public List<Agent> getAgents(@RequestParam("category") Category category){
		return agentService.getAgentsList(category);
	}

	
//	to Assign A particuar ticket
	
	@PostMapping("/{ticketid}/assign/{agentId}")
	public ResponseEntity<String> assignAgents(@PathVariable int ticketid,@PathVariable int agentId) {
		agentService.assignTickets(ticketid,agentId);
		return ResponseEntity.ok("Ticket Assigned Succesfully");
		
	}
	
//	To Solved a Ticket
	@PostMapping("/{ticketid}/closedticket/{agentId}")
	public ResponseEntity<String> closeAsignedTickets(@PathVariable int ticketid,@PathVariable int agentId) {
		agentService.closeTickets(ticketid,agentId);
		return ResponseEntity.ok("Ticket Assigned Succesfully");
		
	}
	
//to close a particular
	public ResponseEntity<String> closeTickets(){
		agentService.closeTicketsAgents();
		return null;
	}
	
	@PostMapping("/{ticketid}/assigned/{agent}")
	 public void ticketsssignment(int ticketid,int agent) {
		agentService.assignToAgents(ticketid,agent); 
	 }

}
