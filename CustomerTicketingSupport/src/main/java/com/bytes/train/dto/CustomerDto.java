package com.bytes.train.dto;

import java.util.Date;

public class CustomerDto {

	private int ticketId;
	private String subject;
	private String description;
	private String priority;
	private String status;
	private Date creation_Date;
	private Date updated_Date;
	private String agentName;

//	public CustomerDto(int ticketId, String subject, String description, String priority, String status,
//			Date creation_Date, Date updated_Date, String agentName) {
//		super();
//		this.ticketId = ticketId;
//		this.subject = subject;
//		this.description = description;
//		this.priority = priority;
//		this.status = status;
//		this.creation_Date = creation_Date;
//		this.updated_Date = updated_Date;
//		this.agentName = agentName;
//	}

	public CustomerDto() {
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreation_Date() {
		return creation_Date;
	}

	public void setCreation_Date(Date creation_Date) {
		this.creation_Date = creation_Date;
	}

	public Date getUpdated_Date() {
		return updated_Date;
	}

	public void setUpdated_Date(Date updated_Date) {
		this.updated_Date = updated_Date;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

}
