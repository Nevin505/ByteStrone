package com.bytes.train.service;



import java.util.List;

import com.bytes.train.entities.Ticket;

public interface ticketService {
	 
	public List<Ticket> getTicket();
	 public void UpdateTicket(int id,Ticket ticket);
	 public void addTicket(Ticket ticke);
}