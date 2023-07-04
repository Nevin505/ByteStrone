package com.bytes.train.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Comment;
import com.bytes.train.entities.Ticket;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	 public List<Comment> findByTicket(Ticket ticket);
	
}