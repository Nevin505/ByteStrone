package com.bytes.train.repos;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
	
	Ticket findByticketId(int id);
	
	@Query("SELECT t FROM Ticket t WHERE t.customer.customerid = :customerId")
    List<Ticket> findByCustomerId(@Param("customerId") int customerId);
	
//	status
	@Query("SELECT t FROM Ticket t WHERE t.status = 'open' ORDER BY t.priority ASC")
	List<Ticket> findOpenTickets();
	
	@Query("SELECT t FROM Ticket t WHERE t.status = 'Assigined' ORDER BY t.priority ASC")
	List<Ticket> findAssiginedTickets();
	
	@Query("Select Count(*) from Ticket t Where t.categoryId.categoryName=:category and t.status in ('Open','Assigined')  ")
    int countTicketPerCategory(@Param("category") String category);
	
	List<Ticket> findByCategoryId_CategoryId(int categoryId);
	
	
	@Query("SELECT COUNT(t) FROM Ticket t WHERE  t.agentId.id = :agentId")
	int getCountByAgentId(@Param("agentId") int agentId); 
	

	
	 @Query("SELECT t FROM Ticket t  WHERE DATE(t.creation_Date) = DATE(:date)")
	 List<Ticket> getTicketVolumesAndCategoryCountsByDate(@Param("date") Date date);
	 
	 @Query("SELECT t FROM Ticket t WHERE DATE(t.creation_Date) BETWEEN :startDate AND :endDate")
	    List<Ticket> findTicketsByDateRange(Date startDate,Date endDate);
	 

	 
	 List<Ticket> findAllByCategoryIdIn(List<Category> ids);
	 
	 
	 List<Ticket> findAllByCategoryIdInAndStatus(List<Category> category,String status );

	List<Ticket> findByCustomer_customeridAndStatus(int cutsomerId, String status);

//	@Query("Select Count(t) from Ticket t Where t.status = :status")
//	int findByStatus(@Param("status") String status);

	List<Ticket> findByStatus(String status);
	
	
	@Query("Select t  from Ticket t Where t.status in ('Open','Assigined') and priority = :priority")
	List<Ticket> findStatusPriorityTickets(String priority);
	





	
	
}
