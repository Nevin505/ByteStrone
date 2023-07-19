package com.bytes.train.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Response;
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
	public ResponseEntity<Response> getTicketVolumes() {
		int ticketCount=supervisorService.getVolume();
		return  ResponseEntity.ok(new Response("The Total Number Of Tickets", ticketCount, true));
	}
	
	
	//To find the Tickets Volumes in each Category Open And Assigined 
	@GetMapping("/report/percategoriestickets")
	public ResponseEntity<Response> getTicketVolume() {
		Map<String,Integer> ticketVolumes=supervisorService.getVolumePerCategory();
		return ResponseEntity.ok(new Response("The Tickets In Category", ticketVolumes, true));
	}
		
	@GetMapping("/agents/alltickets")
	public ResponseEntity<Response> getallTickets(){		
		 Map<String,Integer> solvedUnsolvedTicketCounts=supervisorService.getSolvedUnsolvedTickets();
		return ResponseEntity.ok(new Response("The Solved Unsolved Ticket Counts", solvedUnsolvedTicketCounts, true));
	}
	
	@GetMapping("/priotityCounts")
	public  ResponseEntity<Response> getStatusCount(){
		
		Map<String,Integer> priorityCounts=supervisorService.getStatusCounts();
		return  ResponseEntity.ok(new Response("The Counts Are", priorityCounts, true));
	}
		
//	To Find The Open Tickets Between Two Dates
	@GetMapping("/date/{startdate}/{enddate}")
	public ResponseEntity<Response> getTicketBetweenDate(@PathVariable("startdate") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date startdate,@PathVariable("enddate") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date enddate){
		List<Ticket> ticketVolumes=supervisorService.volumesOpenClosedDate(startdate,enddate);
		return ResponseEntity.ok(new Response("The Tickets On This Date", ticketVolumes, true));
	}
		
}

