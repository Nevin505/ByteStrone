package com.bytes.train.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.CategoryRespository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.SupervisorService;
@Service
public class SupervisorServiceImpl implements SupervisorService {
   
	@Autowired
	CategoryRespository categoryRespository;
	
	@Autowired
	TicketRepository ticketDao;
	
	@Override
	public Map<String, Integer> getVolume() {
		Map<String, Integer> ticketVolumes=new HashMap<>();
		
		 List<Category> category= categoryRespository.findAll();
		 List<Ticket>  tickets=ticketDao.findAll();
		
		 for (Ticket ticket : tickets) {
			 String category1=ticket.getCategoryId().getCategory_name();
		     ticketVolumes.put(category1, ticketVolumes.getOrDefault(category1, 0)+1) ;
		}
		 
		return ticketVolumes;
	}

	@Override
	public Map<String,Long> getResponseTime() {
		Map<String,Long> responseTime=new HashMap<>();
		Date responseTime1;
		Date closedTimeDate;
		List<Ticket> ticket=ticketDao.findAll();
		for (Ticket ticket2 : ticket) {
			String agentname=ticket2.getAgentId().getAgentName();
			responseTime1=ticket2.getUpdated_Date();
			closedTimeDate=ticket2.getClosedDate();
			long responseDuration=(long) (closedTimeDate.getTime()-responseTime1.getTime()/ (1000.0 * 60.0));
			responseTime.put(agentname,responseDuration);			
		}
		return responseTime;
	}

	@Override
	public Map<String, Integer> getCount() {
		  Map<String,Integer> ticketCount=new HashMap<>();
		  List<Ticket> ticket= ticketDao.findAll();
		   
		  for (Ticket ticket2 : ticket) {
			 
			  if(ticket2.getAgentId().getAgentName()!=null) {
				  ticketCount.put(ticket2.getAgentId().getAgentName(), ticketDao.getCountByAgentId(ticket2.getAgentId().getAgentID()));
			  }
		}
		
		return ticketCount;
	}
	

}
