package com.bytes.train.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	Ticket findByticketId(int id);

//	@Query("SELECT t FROM Ticket t WHERE t.customer.id = :customerId")
	List<Ticket> findByCustomer_id(@Param("customerId") int customerId);

//	status
	@Query("SELECT t FROM Ticket t WHERE t.status = 'open' ORDER BY t.priority ASC")
	List<Ticket> findOpenTickets();

	@Query("SELECT t FROM Ticket t WHERE t.status = 'Assigined' ORDER BY t.priority ASC")
	List<Ticket> findAssiginedTickets();

	List<Ticket> findByAgentIdAndStatus(Agent agent, String status);

	@Query("Select Count(*) from Ticket t Where t.categoryId.categoryName=:category and t.status in ('Open','Assigined')  ")
	int countTicketPerCategory(@Param("category") String category);

	List<Ticket> findByCategoryId_CategoryId(int categoryId);

	List<Ticket> findAllByCategoryIdInAndStatusIn(List<Category> ids, String[] Status);

	@Query("SELECT COUNT(t) FROM Ticket t WHERE  t.agentId.id = :agentId")
	int getCountByAgentId(@Param("agentId") int agentId);

	@Query("SELECT t FROM Ticket t  WHERE DATE(t.creation_Date) = DATE(:date)")
	List<Ticket> getTicketVolumesAndCategoryCountsByDate(@Param("date") Date date);

	@Query("SELECT t FROM Ticket t WHERE DATE(t.creation_Date) BETWEEN :startDate AND :endDate")
	List<Ticket> findTicketsByDateRange(Date startDate, Date endDate);

	List<Ticket> findAllByCategoryIdIn(List<Category> ids);

	List<Ticket> findAllByCategoryIdInAndStatus(List<Category> category, String status);
	
//	
	List<Ticket> findBySubjectIgnoreCaseContaining(String subject);	

	
//	Search Jpa's
	List<Ticket> findByCategoryIdInAndStatusAndTicketId(List<Category> category, String status,int ticketId);
	
	List<Ticket> findByCategoryIdInAndStatusAndSubjectIgnoreCaseContaining(List<Category> category, String status,String subject);
	
	List<Ticket> findAllByCategoryIdInAndStatusInAndAndTicketId(List<Category> category,String[] Status,int ticketId);
	
	List<Ticket> findByCategoryIdInAndStatusAndSubjectIgnoreCaseContainingOrTicketIdAndStatus(List<Category> category, String status,String subject,int ticketId,String status1);
	
	List<Ticket> findAllByCategoryIdInAndStatusInAndAndSubjectIgnoreCaseContaining(List<Category> category, String[] status,String subject);
	
	List<Ticket> findAllByCategoryIdInAndStatusInAndAndSubjectIgnoreCaseContainingOrTicketId(List<Category> category, String[] status,String subject,int ticketId);
	
	 @Query("SELECT t FROM Ticket t WHERE (:ticketId IS NULL OR t.ticketId = :ticketId) OR (:subject IS NULL OR t.subject = :subject)")
	 List<Ticket> findTickets(@Param("ticketId") int ticketId, @Param("subject") String subject);
//

	List<Ticket> findByCustomer_idAndStatus(int cutsomerId, String status);

	List<Ticket> findByStatus(String status);
	
	List<Ticket> findByStatusAndCustomer(String status,Customer customer);
	
	List<Ticket> findByCustomer(Customer customer);

	@Query("Select t  from Ticket t Where t.status in ('Open','Assigined') and priority = :priority")
	List<Ticket> findStatusPriorityTickets(String priority);

	@Query("SELECT t FROM Ticket t WHERE DATE(t.creation_Date) BETWEEN :startDate AND :endDate OR DATE(t.closedDate) BETWEEN :startDate AND :endDate")
	List<Ticket> findTicketsByClosedOpenedDateRange(Date startDate, Date endDate);
	
//	Customer Search JPA Query
	List<Ticket> findByStatusAndCustomerAndTicketId(String status,Customer cutsomer,int ticketId);
	
	List<Ticket> findByStatusAndCustomerAndSubjectIgnoreCaseContaining(String status,Customer cutsomer,String subject);
	
	List<Ticket> findByStatusAndCustomerAndSubjectIgnoreCaseContainingOrTicketIdAndStatus(String status,Customer cutsomer,String subject,int ticketId,String searchStatus);
	
	List<Ticket> findByCustomerAndTicketId(Customer cutsomer,int ticketId);
	
	List<Ticket> findByCustomerAndSubjectIgnoreCaseContaining(Customer cutsomer,String subject);
	
	List<Ticket> findByCustomerAndSubjectIgnoreCaseContainingOrTicketId(Customer cutsomer,String subject,int ticketId);
	
	
	
	
//	findByCustomerAndSubjectIgnoreCaseContainingOrTicketId

}


