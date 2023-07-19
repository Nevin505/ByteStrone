package com.bytes.train.controller;

import java.util.List;

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
import com.bytes.train.entities.Response;
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
	public ResponseEntity<Response> saveTicketByPostman(@PathVariable int id, @RequestBody Ticket ticket) {
		try {
			System.out.println(ticket);
			Ticket newTicket = ticketService.saveTicketPostman(id, ticket);
			return ResponseEntity.ok(new Response("The Ticket Was Being Added", newTicket, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}

	}

	@GetMapping("/getTicket/{ticketId}")
	public ResponseEntity<Response> getTicket(@PathVariable int ticketId) {
		Ticket singleTicket = ticketService.getCustomerSingleTicket(ticketId);
		return ResponseEntity.ok(new Response("The Ticket is", singleTicket, true));
	}

	@GetMapping("/values")
	public ResponseEntity<Response> getMapping() {
		List<Category> category = ticketService.optionsvalue();
		return ResponseEntity.ok(new Response("The Tickets Category Are", category, true));
	}

}
