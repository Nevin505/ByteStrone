package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.TicketDao;
import com.bytes.train.service.ticketService;

@Service
public class TicketService implements ticketService {
	@Autowired

	TicketDao ticketDao;

	@Override
	public List<Ticket> getTicket() {
		return ticketDao.findAll();
	}

	@Override
	public String saveTicket(@RequestBody Ticket ticket) {
		ticketDao.save(ticket);
		return "Ticket raised";
	}

	@Override
	@SuppressWarnings("deprecation")
	public void UpdateTicket(int id, Ticket ticket) 
	{

		Ticket ticket2 = ticketDao.getById(id);
		ticket2.setSubject(ticket.getSubject());
		ticket2.getUpdated_Date();
		System.out.println(ticket2);
		ticketDao.save(ticket2);

	}

}
