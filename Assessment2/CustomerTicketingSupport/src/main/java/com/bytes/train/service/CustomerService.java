package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;

public interface CustomerService {
	
	public Customer checkAccess(String userName, String password);
	
	public List<Ticket> getTicketDetails(int id);

	public String setTicketSatisfactoryRating(int ticketId, Float rating)  ;

	public List<Ticket> getFilteredCustomerTickets(int cutsomerId,String Status);

	public List<Ticket> getSearch(int agentId, SearchCriteria searchCriteria);


}
