package com.bytes.train.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/agentsticketcount/{agentId}")
	public int getTicketCount(@PathVariable int agentId) {
		return supervisorService.getAgentsTicketCount(agentId);
		
	}
	
	@GetMapping("/report/tickets")
	public Map<String, Integer> getTicketVolumes() {
		return supervisorService.getVolume();
	}
	
//	To Find The Tickets Assigined Per Agent
	@GetMapping("/ticketsPeragent")
	public Map<String, Integer> getAgentTicketVolumeAssigined(){
		return supervisorService.getAgentPerTicketAssigined();
	}
	
//	@GetMapping("/closedticketsbyagents")
//	public Ma
	
	
}


