package com.bytes.train.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.dto.CustomerDto;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
	 @Autowired
	 CustomerService customerService;
	
	 
	 
//	 to get  all the Customer details
	@GetMapping("/getvalue")
     public List<Customer> getdata() {
    	 return  customerService.getCustomerDetails();
     }
	
	//to add details to the customer table via postman
	@PostMapping("/postvalue")
	public void addData(@RequestBody Customer customer) {
   	 customerService.addCustomerDetails(customer);
    }
	
	//get the  all details of the ticket Raised By the Customer
	@GetMapping("/getCustomerTicketDetails/{id}")
	public List<Ticket> getTickeDataByCustomerId(@PathVariable int id){
		return customerService.getTicketDetails(id);
	}
	
	//to get the ticket details of a particular Persons 
	@GetMapping("/getCustomerDtoTicketDetails/{id}")
	public List<CustomerDto> getCustomerDtoDeatils(@PathVariable int id){
		return customerService.getCustomerDto(id);
	}
//	To Delete 
	
	
 	
}
