package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Comment;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CommentRepository;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	AgentRepository agentRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Comment setComment(int agentId, int ticketId, Comment comment) {

		Agent agent = agentRepository.findById(agentId).orElse(null);
		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		System.out.println(agentId);
		System.out.println(ticketId);
		System.out.println(ticket.getAgentId().getAgentID());
		System.out.println(agent.getAgentID());
		if (ticket.getAgentId().getAgentID() == agent.getAgentID()) {
			ticket.setAgentId(agent);
			comment.setTicket(ticket);
			commentRepository.save(comment);
			return comment;
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> getComment(int ticketId) {
		 
		Ticket ticket=ticketRepository.findById(ticketId).orElse(null);
//		commentRepository.findByTicket_ticketId(ticketId); By using Id
				return commentRepository.findByTicket(ticket);
	}
	
    @Override
	public Comment setCustomerComments(int customerId, int ticketId, Comment comment) {
		
		Customer customer=customerRepository.findById(customerId).orElse(null);
		Ticket ticket=ticketRepository.findById(ticketId).orElse(null);
		
		if(ticket.getAgentId()!=null && ticket.getCustomer().getCustomerid()==customer.getCustomerid()) {
			System.out.println("Here");
			comment.setTicket(ticket);
			commentRepository.save(comment);	
			return comment;
		}
		System.out.println("Returning");
		return null;
	}

	@Override
	public List<Comment> getAgentCustomerChat( int ticketId) {
		 
		Ticket ticket=ticketRepository.findById(ticketId).orElse(null);
		
		List<Comment> comment=commentRepository.findByTicket(ticket);
		
		return comment;
	}
    
   
	

}
