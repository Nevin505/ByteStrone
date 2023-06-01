package com.bytes.train.entities;


import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Comment {
	@Id
	private int commentID;
	private String content;
	private String author;
	private LocalDate timeStamp;
	@ManyToOne
	@JoinColumn(name="ticketId")
	Ticket ticket;
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
