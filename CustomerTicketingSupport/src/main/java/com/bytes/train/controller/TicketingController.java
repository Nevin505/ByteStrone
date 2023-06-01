package com.bytes.train.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Ticket;
import com.bytes.train.service.ticketService;

@RestController
@RequestMapping("/ticket")
public class TicketingController {
	@Autowired
	ticketService ticketService;
	@GetMapping("/query")
	public List<Ticket> getTicketInfo() {
		return ticketService.getTicket();
		}

	@PostMapping("/query1")
	public void saveTicket(@RequestBody Ticket ticket) {
		ticketService.addTicket(ticket);
		}
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable("id") String id, @RequestBody Ticket ticket) {
//		ticketService.UpdateTicket(null);
		
	}
	
	
}
