package com.bytes.train.entities;


import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table

public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticketId;
	private String subject;
	private String description;
	private String priority;
	private String status;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp without time zone", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation_Date;
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp without time zone", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_Date; 
	
	@ManyToOne
	@JoinColumn(name="customerid")
	Customer customerId;
	
	@ManyToOne
	@JoinColumn(name="agentID")
	Agent agentId;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	Category categoryId;
	
	
	
	
	private Date closeddate;
	private String satisfactionRating;

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
	public Date getCloseddate() {
		return closeddate;
	}
	public void setCloseddate(Date closeddate) {
		this.closeddate = closeddate;
	}
	public String getSatisfactionRating() {
		return satisfactionRating;
	}
	public void setSatisfactionRating(String satisfactionRating) {
		this.satisfactionRating = satisfactionRating;
	}

	
}