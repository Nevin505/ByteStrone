package com.bytes.train.service;


import java.util.List;

import com.bytes.train.entities.Customer;

public interface CustomerService {
	 public List<Customer> getCustomerDetails();
	    public void addCustomerDetails(Customer customer);
	    public void addTicketDetails();
	   
	    
		

}
