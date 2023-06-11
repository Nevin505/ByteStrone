package com.bytes.train.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.service.SupervisorService;
import com.bytes.train.service.TicketService;

@RestController
@RequestMapping("/supervisor")
@CrossOrigin("http://localhost:4200")
public class SuperVisorController {
	
	@Autowired
	 TicketService ticketService;
	
	@Autowired
	 SupervisorService supervisorService;
	
	// To Get The report
		@GetMapping("/report/tickets")
		public Map<String, Integer> getTicketVolumes() {
			return supervisorService.getVolume();
		}

//		To Get The Mapping
		@GetMapping("/report/response")
		public Map<String, Long> getTicketResponse() {
			return supervisorService.getResponseTime();
		}
		
		@GetMapping("/count")
		public Map<String,Integer> agentTicketCount(){
			return supervisorService.getCount();
		}

}
