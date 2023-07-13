package com.bytes.train.controller;

import java.util.List;
import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.service.TicketService;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("http://localhost:4200")
public class TicketingController {

	@Autowired
	TicketService ticketService;

	@Autowired
//	CategoryRespository categoryDao;

	@GetMapping("/query")
	public List<Ticket> getTicketInfo() {
		return ticketService.getTicket();
	}

	@PostMapping("/addticket/{id}")
	public ResponseEntity<Ticket> saveTicketByPostman(@PathVariable int id, @RequestBody Ticket ticket) {
		System.out.println(ticket);
		return ResponseEntity.ok(ticketService.saveTicketPostman(id, ticket));
	}

	// Assigination of tickets to a Agent
	@PostMapping("/{ticketid}/assign/{agentId}")
	public ResponseEntity<String> assignAgents(@PathVariable int ticketid, @PathVariable int agentId) {
		ticketService.assignTickets(ticketid, agentId);
		return ResponseEntity.ok("Ticket Assigned Succesfully");

	}

	@GetMapping("/getTicket/{ticketId}")
	public ResponseEntity<Ticket> getTicket(@PathVariable int ticketId) {
		return ResponseEntity.ok(ticketService.getCustomerSingleTicket(ticketId));
	}
	
	
	
	

	@GetMapping("/values")
	public List<Category> getMapping() {
		return ticketService.optionsvalue();
	}
	
	
	
}