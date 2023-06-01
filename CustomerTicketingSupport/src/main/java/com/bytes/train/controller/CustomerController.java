package com.bytes.train.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Customer;
import com.bytes.train.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	 @Autowired
	 CustomerService customerService;
	
	@GetMapping("/getvalue")
     public List<Customer> getdata() {
    	 return  customerService.getCustomerDetails();
     }
	@PostMapping("/postvalue")
	public void addData(@RequestBody Customer customer) {
   	 customerService.addCustomerDetails(customer);
    }
	
}
