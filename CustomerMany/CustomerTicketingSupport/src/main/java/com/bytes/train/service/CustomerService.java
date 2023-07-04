package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;

public interface CustomerService {
	
	public Customer checkAccess(String userName, String password);
	
	public List<Ticket> getTicketDetails(int id);

}
