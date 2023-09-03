package com.bytes.train.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Comment;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CommentRepository;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Comment setComment(int agentId, int ticketId, Comment comment) throws Exception {

		Agent agent = agentRepository.findById(agentId).orElse(null);
		if(agent==null) {
			throw new ResourceNotFoundException("There Exists No Agent");
		}
		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		if(ticket==null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		System.out.println(comment.getContent().length());
		if (ticket.getAgentId().getId() == agent.getId()&& ticket.getStatus().equals("Assigned") &&comment.getContent().length()!=0){
			ticket.setAgentId(agent);
			comment.setTicket(ticket);
			comment.setSeen("Not Seen");
			comment.setTimeStamp(new Date());
			commentRepository.save(comment);
			return comment;
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> getComment(int ticketId) throws Exception {

		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		if(ticket==null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		return commentRepository.findByTicket(ticket);
	}

	@Override
	public Comment setCustomerComments(int customerId, int ticketId, Comment comment) throws Exception {

		Customer customer = customerRepository.findById(customerId).orElse(null);
		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		if (customer == null) {
			throw new ResourceNotFoundException("Customer Id Doesn't Exist");
		}
		if (ticket == null) {
			throw new ResourceNotFoundException("Ticket Id Doesn't Exist");
		}

		if (ticket.getAgentId() != null && ticket.getCustomer().getId() == customer.getId()&&comment.getContent().length()!=0) {
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
	public List<Comment> getAgentCustomerChat(int ticketId) throws Exception {
		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		if(ticket==null) {
			throw new ResourceNotFoundException("There Exist No Ticket");
		}
		List<Comment> comment = commentRepository.findByTicket(ticket);
		return comment;
	}

}
