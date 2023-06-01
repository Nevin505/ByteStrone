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
	public void addTicket(@RequestBody Ticket ticket) {
		ticketDao.save(ticket);
		}
	@Override
	public void UpdateTicket(int id, Ticket ticket) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void UpdateTicket(int id, Ticket ticket) {
////		// TODO Auto-generated method stub	
////				Ticket ticket2=ticketDao.getTicketById(id);
////				ticket2.setSubject(ticket.getSubject());
//		
//	}
	
	
}
