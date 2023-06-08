package com.bytes.train.repos;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
	
	Ticket findByticketId(int id);
	
	@Query("SELECT t FROM Ticket t WHERE t.customer.customerid = :customerId")
    List<Ticket> findByCustomerId(@Param("customerId") int customerId);
	
//	status
	@Query("SELECT t FROM Ticket t WHERE t.status = 'open' ORDER BY t.priority ASC")
	List<Ticket> findOpenTickets();
	
//	@Query("SELECT t.ticketId FROM Ticket t WHERE t.categoryId.categoryId = :categoryId")
//	Ticket  findByfindTicketIdByCategoryId(@Param("categoryId") Integer categoryId);
	
//	int findByCategoryId(var1);
	

//	Ticket findByCategoryId_CategoryId(int categoryId); 
	
	List<Ticket> findByCategoryId_CategoryId(int categoryId); 

//	Ticket findByCategoryId(int var);
	  
//	 Ticket findByfindTicketIdByCategoryId(Category category);
////	to sort
//	List<Ticket> findAllByStatusOrderByPriorityDesc();
//	
//	 @Query("SELECT * FROM Ticket WHERE status = 'open' ORDER BY "
//	            + "CASE priority "
//	            + "WHEN 'High' THEN 1 "
//	            + "WHEN 'Medium' THEN 2 "
//	            + "WHEN 'Low' THEN 3 "
//	            + "ELSE 4 END, status")
//	    List<Ticket> findOpenTicketsSortedByPriorityAndStatus();
	
	
	
	
}
