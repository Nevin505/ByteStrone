package com.bytes.train.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Comment;
import com.bytes.train.entities.Response;
import com.bytes.train.service.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin("http://localhost:4200")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/addcomments/{agentId}/{ticketId}")
	public ResponseEntity<Response> addAgentComment(@PathVariable int agentId, @PathVariable int ticketId,
			@RequestBody Comment comment) {
		try {
			Comment comment2 = commentService.setComment(agentId, ticketId, comment); 
			if (comment2 == null) {
				return ResponseEntity.ok(new Response("The Comment Was Not Added", comment2, false));
			}
			return ResponseEntity.ok(new Response("The Comment Was Added", comment2, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}

	}

	@GetMapping("/ticketcomments/{ticketId}")
	public ResponseEntity<Response> getAllComments(@PathVariable("ticketId") int ticketId) {
		try {
			List<Comment> comments = commentService.getComment(ticketId);
			return ResponseEntity.ok(new Response("The Comments Related To These Tickets", comments, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}
	}

	@PostMapping("/addcustomercomments/{customerId}/{ticketId}")
	public ResponseEntity<Response> addCustomerComment(@PathVariable int customerId, @PathVariable int ticketId,
			@RequestBody Comment comment) {
		try {
			Comment customerComment = commentService.setCustomerComments(customerId, ticketId, comment);
			if (customerComment == null) {
				return ResponseEntity.ok(new Response("The Comment is not being Added", null, false));
			} else {
				return ResponseEntity.ok(new Response("The Comment is Being Added", customerComment, true));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}
	}

	@GetMapping("/agentCustomerConversation/{ticketId}")
	public ResponseEntity<Response> getAgentCustomerChat(@PathVariable int ticketId) {
		try {
			List<Comment> agentCustomerChat = commentService.getAgentCustomerChat(ticketId);
			return ResponseEntity.ok(new Response("The Agent Customer Chats", agentCustomerChat, true));
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("Wrong", null, false));
		}
	}

}
