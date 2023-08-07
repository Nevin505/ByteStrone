package com.bytes.train.serviceimpl;

import java.util.Date;
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

		Agent agent = agentRepository.findById(agentId).orElseThrow();
		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
		if (ticket.getAgentId().getId() == agent.getId()) {
			ticket.setAgentId(agent);
			comment.setTicket(ticket);
			comment.setTimeStamp(new Date());
			commentRepository.save(comment);
			return comment;
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> getComment(int ticketId) {

		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
		return commentRepository.findByTicket(ticket);
	}

	@Override
	public Comment setCustomerComments(int customerId, int ticketId, Comment comment) throws Exception {

		Customer customer = customerRepository.findById(customerId).orElse(null);
		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		if (customer == null) {
			throw new Exception("Customer Id Doesn't Exist");
		}
		if (ticket == null) {
			throw new Exception("Ticket Id Doesn't Exist");
		}

		if (ticket.getAgentId() != null && ticket.getCustomer().getId() == customer.getId()) {
			System.out.println("Here");
			comment.setTicket(ticket);
			comment.setTimeStamp(new Date());
			commentRepository.save(comment);
			return comment;
		} else {
			return null;
		}
	}
	
	@Override
	public List<Comment> getAgentCustomerChat(int ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
		List<Comment> comment = commentRepository.findByTicket(ticket);
		return comment;
	}

}
