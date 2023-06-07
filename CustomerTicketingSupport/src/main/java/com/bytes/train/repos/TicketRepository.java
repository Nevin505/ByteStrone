package com.bytes.train.repos;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bytes.train.entities.Ticket;

public interface TicketDao extends JpaRepository<Ticket,Integer> {
	Ticket findByticketId(int id);
//	Customer customer
	
//	ticketId;
//	private String subject;
//	private String description;
//	private String priority;
//	private String status="open";
	
	@Query("SELECT t FROM Ticket t WHERE t.customer.customerid = :customerId")
    List<Ticket> findByCustomerId(@Param("customerId") int customerId);
	
	
	
	
}
