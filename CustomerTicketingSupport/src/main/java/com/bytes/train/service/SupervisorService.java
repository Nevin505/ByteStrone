package com.bytes.train.service;

import java.util.Map;

public interface SupervisorService {
	
	public Map<String, Integer> getVolume();
	
	public Map<String, Long> getResponseTime();

	public Map<String, Integer> getCount();
	
	

}
