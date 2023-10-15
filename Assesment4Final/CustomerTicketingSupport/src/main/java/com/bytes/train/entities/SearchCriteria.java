package com.bytes.train.entities;

public class SearchCriteria {
	
	int ticketId;
	
	String subject;
	
	String description;
	
	String status;
	
	public SearchCriteria() {
		
	}

	public SearchCriteria(int ticketId, String subject, String description, String status) {
		super();
		this.ticketId = ticketId;
		this.subject = subject;
		this.description = description;
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
