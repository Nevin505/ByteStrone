package com.bytes.train.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.CategoryRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.SupervisorService;

@Service
public class SupervisorServiceImpl implements SupervisorService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	AgentRepository agentRepository;

	@Override
	public int getAgentsTicketCount(int agentId) {
		return ticketRepository.getCountByAgentId(agentId);
	}

	@Override
	public Map<String, Integer> getVolume() {

		Map<String, Integer> ticketVolumes = new HashMap<>();
		List<Category> category = categoryRepository.findAll();
		List<Ticket> tickets = ticketRepository.findAll();

		for (Category categorykey : category) {
			ticketVolumes.put(categorykey.getCategory_name(), 0);

		}
		for (Ticket ticket : tickets) {

			ticketVolumes.put(ticket.getCategoryName(), ticketVolumes.getOrDefault(ticket.getCategoryName(), 0) + 1);

		}
		return ticketVolumes;
	}
	
	
	@Override
	public Map<String, Integer> getAgentPerTicketAssigined() {
		
		Map<String, Integer> ticketsPer=new HashMap<>();
		
		List<Agent> fullagents=agentRepository.findAll();
		
		List<Ticket> allAssiginedTickets=ticketRepository.findAssiginedTickets();
		
		for (Agent agent : fullagents) {
			ticketsPer.put(agent.getAgentName(),0);
		}
		
		for (Ticket ticket : allAssiginedTickets) {
			
			ticketsPer.put(ticket.getAgentId().getAgentName(),ticketsPer.getOrDefault(ticket.getAgentId().getAgentName(),0)+1 );
		}
		return ticketsPer;
	}
	
	
   
	 
	
	
	
	
	
	
	
	
	
//	public Map<String, Integer> getVolume() {
//		Map<String, Integer> ticketVolumes=new HashMap<>();
//		
//		 List<Category> category= categoryRespository.findAll();
//		 List<Ticket>  tickets=ticketDao.findAll();
//		
//		 for (Ticket ticket : tickets) {
//			 String category1=ticket.getCategoryId().getCategory_name();
//		     ticketVolumes.put(category1, ticketVolumes.getOrDefault(category1, 0)+1) ;
//		}
//		 
//		return ticketVolumes;
//	}
//
//	
//	public Map<String,Long> getResponseTime() {
//		Map<String,Long> responseTime=new HashMap<>();
//		Date responseTime1;
//		Date closedTimeDate;
//		List<Ticket> ticket=ticketDao.findAll();
//		for (Ticket ticket2 : ticket) {
//			String agentname=ticket2.getAgentId().getAgentName();
//			responseTime1=ticket2.getUpdated_Date();
//			closedTimeDate=ticket2.getClosedDate();
//			long responseDuration=(long) (closedTimeDate.getTime()-responseTime1.getTime()/ (1000.0 * 60.0));
//			responseTime.put(agentname,responseDuration);			
//		}
//		return responseTime;
//	}
//
//	public Map<String, Integer> getCount() {
//		  Map<String,Integer> ticketCount=new HashMap<>();
//		  List<Ticket> ticket= ticketDao.findAll();
//		   
//		  for (Ticket ticket2 : ticket) {
//			 
//			  if(ticket2.getAgentId().getAgentName()!=null) {
//				  ticketCount.put(ticket2.getAgentId().getAgentName(), ticketDao.getCountByAgentId(ticket2.getAgentId().getAgentID()));
//			  }
//		}
//		
//		return ticketCount;
//	}
//	


	
}
