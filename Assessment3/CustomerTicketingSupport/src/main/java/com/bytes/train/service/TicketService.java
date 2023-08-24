package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;

public interface TicketService {

	public List<Ticket> getTicket();

	Ticket saveTicketPostman(int id, Ticket ticket) throws Exception;

	public List<Category> optionsvalue();

	public Ticket getCustomerSingleTicket(int ticketId);

	public Ticket assiginationTickets( int ticketId) throws ResourceNotFoundException;


}
