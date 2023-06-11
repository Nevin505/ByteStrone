package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
}