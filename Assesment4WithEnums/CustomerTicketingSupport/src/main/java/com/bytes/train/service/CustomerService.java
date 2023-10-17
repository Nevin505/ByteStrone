package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.StatusEnum;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;

public interface CustomerService {
	
//	public Customer checkAccess(String userName, String password);
	
	public List<Ticket> getTicketDetails(int id) throws Exception;

	public String setTicketSatisfactoryRating(int ticketId, Float rating) throws Exception  ;

	public List<Ticket> getFilteredCustomerTickets(int cutsomerId,StatusEnum Status) throws ResourceNotFoundException;

	public List<Ticket> getSearch(int customerId, SearchCriteria searchCriteria) throws Exception;


}
