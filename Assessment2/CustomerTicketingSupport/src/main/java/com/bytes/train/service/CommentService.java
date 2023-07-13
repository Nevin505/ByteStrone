//package com.bytes.train.service;
//
//import java.util.List;
//
//import com.bytes.train.entities.Customer;
//import com.bytes.train.entities.Ticket;
//
//
//public interface CustomerService {
//	
//	public Customer checkAccess(String userName, String password);
//	
//	public List<Ticket> getTicketDetails(int custId);
//	
//}
package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Comment;

public interface CommentService {

	public Comment setComment(int agentId,int ticketId,Comment comment);
	
	public List<Comment> getComment(int ticketId);

	public Comment setCustomerComments(int customerId, int ticketId, Comment comment);

	public List<Comment> getAgentCustomerChat(int ticketId);

}
