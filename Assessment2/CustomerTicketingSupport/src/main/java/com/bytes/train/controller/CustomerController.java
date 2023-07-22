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
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.FilterCreteria;
import com.bytes.train.entities.Response;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;
import com.bytes.train.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
//	CommentService ;
 
	@PostMapping("/access")
	public ResponseEntity<Response> checkAccess(@RequestBody Customer customer) {
		try {
		String userName=customer.getUsername();
		String password=customer.getUserpassword();
		Customer response = customerService.checkAccess(userName, password);	
		if(response == null) {
			return ResponseEntity.ok(new Response("Invalid Login",null,false));
		}
		return ResponseEntity.ok( new Response("Successfull Login", response, true));
		}
		catch(Exception e){
			return ResponseEntity.ok(new Response( "Error",null,false)); 
		}
    }
	
	// get the all details of the ticket Raised By the Customer
		@GetMapping("/getCustomerTicketDetails/{id}")
		public ResponseEntity<Response> getTickeDataByCustomerId(@PathVariable int id) {
//			try {
				List<Ticket> customerTickets= customerService.getTicketDetails(id);
				return ResponseEntity.ok(new Response("The Customer Tickets Are", customerTickets, true));
//			} catch (Exception e) {
//				return ResponseEntity.ok(new Response(e.getMessage(), null, false));
//			}
		}
		
		@PutMapping("/setSatisfactoryRating/{ticketId}/{rating}")
		public ResponseEntity<Response> setSatisfactoryRating(@PathVariable("ticketId") int ticketId, @PathVariable("rating") float rating){
			
			try {
				String mssg= customerService.setTicketSatisfactoryRating(ticketId,rating);
				return ResponseEntity.ok(new Response(mssg,null,true));
			} catch (Exception e) {
			  return ResponseEntity.ok(new Response(e.getMessage(),null, false));
			}
			
		}
		
//		Customer Closed,Assogined And Open Tickets Filter
		@PostMapping("/filtercustomertickets/{cutsomerId}")
		public ResponseEntity<Response> getOpenTickets(@PathVariable("cutsomerId") int customerId,@RequestBody FilterCreteria filterCreteria){
			String status=filterCreteria.getStatus();		
			if(status.equalsIgnoreCase("Open")) {
				System.out.println("Here");
				List<Ticket> customerOpenedTickets=customerService.getFilteredCustomerTickets(customerId,status);
			return ResponseEntity.ok(new Response("The Opened Tickets Are", customerOpenedTickets, true));
		}
			else if(status.equalsIgnoreCase("Assigined")) {
				List<Ticket> customerAssiginedTickets=customerService.getFilteredCustomerTickets(customerId,status);
				return ResponseEntity.ok(new Response("The Assigined Tickets Are", customerAssiginedTickets, true));
			}
			else {
				List<Ticket> getClosedTickets=customerService.getFilteredCustomerTickets(customerId,status);
				return ResponseEntity.ok(new Response("The Tickets Are", getClosedTickets, true));
			}
		}
		
		@GetMapping("/search/{agentId}")
		public ResponseEntity<Response> searchTickets(@PathVariable("agentId") int agentId,
				@RequestBody SearchCriteria searchCriteria) {
			try {
				List<Ticket> searchTickets = customerService.getSearch(agentId, searchCriteria);
				return ResponseEntity.ok(new Response("The Search Result Is", searchTickets, true));
			} catch (Exception e) {
				return ResponseEntity.ok(new Response("Error", null, false));
			}
		}
		 
}