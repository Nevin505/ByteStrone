package com.bytes.train.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.CategoryRespository;
import com.bytes.train.service.TicketService;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("http://localhost:4200")
public class TicketingController {
	
	@Autowired
	TicketService ticketService;
	
	 @Autowired
	 CategoryRespository categoryDao;
	 
	@GetMapping("/query")
	public List<Ticket> getTicketInfo() {
		return ticketService.getTicket();
	}

	// Via Postman
	@PostMapping("/addticket/{id}")
	public ResponseEntity<String> saveTicketByPostman(@PathVariable int id, @RequestBody Ticket ticket) {
		System.out.println(ticket);
//		String categoryName=ticket.getCategoryName();
//		System.out.println(categoryName);
		ticketService.saveTicketPostman(id, ticket);
		return ResponseEntity.ok("Ticket is Raised");
	}

//Assogination of tickets to a Agent
	@PostMapping("/{ticketid}/assign/{agentId}")
	public ResponseEntity<String> assignAgents(@PathVariable int ticketid,@PathVariable int agentId) {
		ticketService.assignTickets(ticketid,agentId);
		return ResponseEntity.ok("Ticket Assigned Succesfully");
		
	}
	 
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable("id") int id, @RequestBody Ticket ticket) {
		ticketService.UpdateTicket(id, ticket);
	}
	
	
	@GetMapping("/values")
	public List<Category> getMapping() {
		return ticketService.optionsvalue();
	}
	
	
	//To Get The report
	@GetMapping("/report/tickets")
	public Map<String, Integer> getTicketVolumes(){
		return ticketService.getVolume();
	}
	
//	To Get The Mapping
	@GetMapping("/report/response")
	public Map<String, Long> getTicketResponse(){
		return ticketService.getResponseTime();
	}
	
}
