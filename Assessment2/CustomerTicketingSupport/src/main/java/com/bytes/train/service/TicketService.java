package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;

public interface TicketService {

	public List<Ticket> getTicket();

	Ticket saveTicketPostman(int id, Ticket ticket) throws Exception;

	public List<Category> optionsvalue();

	public Ticket getCustomerSingleTicket(int ticketId);

	public void assiginationTickets( int ticketId);

}
