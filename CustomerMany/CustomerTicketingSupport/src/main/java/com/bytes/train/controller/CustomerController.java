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

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Response;
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
			return ResponseEntity.ok(new Response(e.getMessage(),null,false)); 
		}
    }
	
	// get the all details of the ticket Raised By the Customer
		@GetMapping("/getCustomerTicketDetails/{id}")
		public List<Ticket> getTickeDataByCustomerId(@PathVariable int id) {
			return customerService.getTicketDetails(id);
		}
		
}