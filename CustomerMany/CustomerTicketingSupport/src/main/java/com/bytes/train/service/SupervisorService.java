package com.bytes.train.service;

import java.util.Map;

public interface SupervisorService {

	public int getAgentsTicketCount(int agentId);

	public Map<String, Integer> getVolume();

	public Map<String, Integer> getAgentPerTicketAssigined();
	
//	public Map<String, Integer> getVolume();
//	
//	public Map<String, Long> getResponseTime();
//
//	public Map<String, Integer> getCount();
	
	

}
