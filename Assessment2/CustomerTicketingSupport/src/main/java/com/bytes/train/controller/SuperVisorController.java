package com.bytes.train.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.SupervisorService;

@RestController
@RequestMapping("/supervisor")
@CrossOrigin("http://localhost:4200")
public class SuperVisorController {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	SupervisorService supervisorService;
	

	
	//To Get All The Tickets Recieved
	@GetMapping("/report/tickets")
	public int getTicketVolumes() {
		return supervisorService.getVolume();
	}
	
	
	//To find the Tickets Volumes in each Category Open And Assigined 
	@GetMapping("/report/percategoriestickets")
	public Map<String, Integer> getTicketVolume() {
		return supervisorService.getVolumePerCategory();
	}
	
	
//	To Find The Tickets On A Particular Date
	@GetMapping("/date/{date}")
	public Map<String, Integer> getVolumeDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date date){
		return supervisorService.getVolumesTicketDate(date);
	}
	
//	To Find The Tickets Between Two Dates
	@GetMapping("/date/{startdate}/{enddate}")
	public Map<String,Integer> getVolumeTicketBetweenDate(@PathVariable("startdate") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date startdate,@PathVariable("enddate") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date enddate){
		return supervisorService.getVolumesBetweenDate(startdate,enddate);
	}
	
	@GetMapping("/agents/alltickets")
	public Map<String,Integer> getallTickets(){
		return supervisorService.getSolvedUnsolvedTickets();
	}

	
	
	@GetMapping("/priotityCounts")
	public Map<String,Integer> getStatusCount(){
		return supervisorService.getStatusCounts();
	}
	
	@GetMapping("/htmlreportdate/{date}")
	public List<Ticket> getHtmlReport(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date date){
		return supervisorService.generateHtMlReport(date);
	}
	
	

	
}


