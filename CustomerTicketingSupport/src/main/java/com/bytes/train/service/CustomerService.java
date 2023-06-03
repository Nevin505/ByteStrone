package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;

public interface CustomerService {
	public List<Customer> getCustomerDetails();

	public Customer addCustomerDetails(Customer customer);
	
	public List<Ticket> getTicketDetails(int custId);

}
