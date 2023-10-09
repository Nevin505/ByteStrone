package com.bytes.train.controller;

import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.FilterCreteria;
import com.bytes.train.entities.Response;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	// get the all details of the ticket Raised By the Customer
	@GetMapping("/getCustomerTicketDetails/{id}")
	public ResponseEntity<Response> getTickeDataByCustomerId(@PathVariable String id) {
		try {

			byte[] decodedBytes = Base64.getDecoder().decode(id);
			String decodedId = new String(decodedBytes);
			int originalId = Integer.parseInt(decodedId);
			
			List<Ticket> customerTickets = customerService.getTicketDetails(originalId);
			
			return ResponseEntity.ok(new Response("The Customer Tickets Are", customerTickets, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Issue Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body((new Response("Some Error Occurred", null, false)));
		} catch (Exception e) {
			logger.error("The Issue Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body((new Response("Some Error Occurred", null, false)));
		}
	}

	@PutMapping("/setSatisfactoryRating/{ticketId}/{rating}")
	public ResponseEntity<Response> setSatisfactoryRating(@PathVariable("ticketId") int ticketId,
			@PathVariable("rating") float rating) {

		try {
			String mssg = customerService.setTicketSatisfactoryRating(ticketId, rating);
			return ResponseEntity.ok(new Response(mssg, null, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Issue Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body((new Response("Some Error Occurred", null, false)));
		} catch (Exception e) {
			logger.error("The Issue Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body((new Response("Some Error Occurred", null, false)));
		}

	}

//		Customer Closed,Assigned And Open Tickets Filter
	@PostMapping("/filtercustomertickets/{cutsomerId}")
	public ResponseEntity<Response> getOpenTickets(@PathVariable("cutsomerId") String customerId,
			@RequestBody FilterCreteria filterCreteria) {
		String status = filterCreteria.getStatus();
		try {
			
			byte[] decodedBytes = Base64.getDecoder().decode(customerId);
			String decodedId = new String(decodedBytes);
			int originalId = Integer.parseInt(decodedId);
			
			List<Ticket> customerOpenedTickets = customerService.getFilteredCustomerTickets(originalId, status);
			
			return ResponseEntity.ok(new Response("The Opened Tickets Are", customerOpenedTickets, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Issue Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Some Error Occurred", null, false));
		} catch (Exception e) {
			logger.error("The Issue Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("Some Error Occurred", null, false));
		}

	}

	@PostMapping("/search/{customerId}")
	public ResponseEntity<Response> searchTickets(@PathVariable("customerId") String customerId,
			@RequestBody SearchCriteria searchCriteria) {
		try {
			
			byte[] decodedBytes = Base64.getDecoder().decode(customerId);
			String decodedId = new String(decodedBytes);
			int originalId = Integer.parseInt(decodedId);
			
			List<Ticket> searchTickets = customerService.getSearch(originalId, searchCriteria);
			return ResponseEntity.ok(new Response("The Search Result Is", searchTickets, true));
		} catch (ResourceNotFoundException e) {
			logger.error("The Issue Is", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Some Error Occurred", null, false));
		} catch (Exception e) {
			logger.error("The Issue Is", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("Some Error Occurred", null, false));

		}
	}

}
