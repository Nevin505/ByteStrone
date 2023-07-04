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
import com.bytes.train.service.CommentService;


@RestController
@RequestMapping("/comment")
@CrossOrigin("http://localhost:4200")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/addcomments/{agentId}/{ticketId}")
	public ResponseEntity<Comment> addAgentComments(@PathVariable int agentId,@PathVariable int ticketId,@RequestBody Comment comment){
		
		return ResponseEntity.ok(commentService.setComment(agentId,ticketId,comment));
	}
	
	@GetMapping("/allComments")
	public  ResponseEntity<List<Comment>> addComments(){  
		
		return ResponseEntity.ok(commentService.getComment());
	}
    
	@PostMapping("/addcustomercomments/{customerId}/{ticketId}")
	public ResponseEntity<Comment> addCustomerComments(@PathVariable int customerId,@PathVariable int ticketId,@RequestBody Comment comment){
		
		return ResponseEntity.ok(commentService.setCustomerComments(customerId,ticketId,comment));
	}
	@GetMapping("/agentCustomerConversation/{ticketId}")
	 public List<Comment> getAgentCustomerChat(@PathVariable int ticketId){
		return commentService.getAgentCustomerChat(ticketId);
		 
	 }
	 

}
