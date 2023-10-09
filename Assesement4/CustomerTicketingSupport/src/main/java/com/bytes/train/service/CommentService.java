package com.bytes.train.service;

import java.util.List;

import com.bytes.train.entities.Comment;

public interface CommentService {

	public Comment setComment(int agentId,int ticketId,Comment comment) throws Exception;
	
	public List<Comment> getComment(int ticketId) throws Exception;

	public Comment setCustomerComments(int customerId, int ticketId, Comment comment) throws Exception;

	public List<Comment> getAgentCustomerChat(int ticketId) throws Exception;



}
