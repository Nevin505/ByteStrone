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
	public  int getVolume() {		
		List<Ticket> fulltickets=ticketRepository.findAll();		
		return fulltickets.size();
	}
	

	@Override
	public Map<String, Integer> getVolumePerCategory() {
		Map<String, Integer> ticketVolumes = new HashMap<>();		
		List<Category> category = categoryRepository.findAll();			
		for (Category categorykey : category) {
			ticketVolumes.put(categorykey.getCategory_name(),ticketRepository.countTicketPerCategory(categorykey.getCategory_name()));
		}		
		return ticketVolumes;		
	}
	
	
	@Override
	public Map<String,Integer> getVolumesTicketDate(Date d) {
		
		List<Category> fullcaCategories=categoryRepository.findAll();		
		List<Ticket> tickets=ticketRepository.getTicketVolumesAndCategoryCountsByDate(d);		
		Map<String, Integer> ticketsVolumesPerDay=new HashMap<String, Integer>();
		
		for (Category category : fullcaCategories) {
			ticketsVolumesPerDay.put(category.getCategory_name(), 0);
			
		}
		
		for (Ticket ticket : tickets) {
			ticketsVolumesPerDay.put(ticket.getCategoryId().getCategory_name(), ticketsVolumesPerDay.getOrDefault(ticket.getCategoryId().getCategory_name(),0)+1);
		}
		return ticketsVolumesPerDay;
	}

	@Override
	public Map<String, Integer> getVolumesBetweenDate(Date startdate,Date enddate) {
		
		Map<String, Integer> ticketsVolumesPerDay=new HashMap<String, Integer>();		
		List<Category> fullcaCategories=categoryRepository.findAll();
		List<Ticket> tickets=ticketRepository.findTicketsByDateRange(startdate,enddate);
		
		for (Category category : fullcaCategories) {
			ticketsVolumesPerDay.put(category.getCategory_name(), 0);			
		}		
		for (Ticket ticket : tickets) {
			ticketsVolumesPerDay.put(ticket.getCategoryId().getCategory_name(), ticketsVolumesPerDay.getOrDefault(ticket.getCategoryId().getCategory_name(),0)+1);
		}
		return ticketsVolumesPerDay;
	}

	@Override
	public Map<String, Integer> getSolvedUnsolvedTickets() {
		
		Map<String, Integer> getAllTickets=new HashMap<>();		
		List<Ticket> tickets=ticketRepository.findAll();		
		int openTickets=(ticketRepository.findByStatus("Open")).size();		
		int assiginedTickets=(ticketRepository.findByStatus("Assigined")).size();		
		int solvedTickets=(ticketRepository.findByStatus("Closed")).size();
		
		getAllTickets.put("Open", openTickets);
		getAllTickets.put("Assigined Tickets",assiginedTickets);
		getAllTickets.put("Closed",solvedTickets);		
		return getAllTickets ;
	}

	
	@Override
	public Map<String, Integer> getStatusCounts() {
		int lowPriority=(ticketRepository.findStatusPriorityTickets("Low")).size();
		int highPriority=(ticketRepository.findStatusPriorityTickets("Medium")).size();
		int MediumPriority=(ticketRepository.findStatusPriorityTickets("High")).size();
		int allPriority=(ticketRepository.findAll()).size();
		
		Map<String,Integer> priorityCounts=new HashMap<>();
		
		priorityCounts.put("Low",lowPriority);
		priorityCounts.put("Medium",MediumPriority);
		priorityCounts.put("High", highPriority);
		priorityCounts.put("All",allPriority);
				
		return priorityCounts;
	}

	@Override
	public List<Ticket> generateHtMlReport(Date d) {
		
		List<Ticket> tickets=ticketRepository.getTicketVolumesAndCategoryCountsByDate(d);
		
		return tickets;
	}





	
}
