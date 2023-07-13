package com.bytes.train.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bytes.train.entities.Ticket;

public interface SupervisorService {

	
	public int getVolume();

	
	
	public Map<String, Integer> getVolumePerCategory() ;

	public Map<String, Integer> getVolumesTicketDate(Date d);


	public Map<String, Integer> getVolumesBetweenDate(Date startdate,Date enddate);

	public Map<String, Integer> getSolvedUnsolvedTickets();

	

	public Map<String, Integer> getStatusCounts();

	public List<Ticket> generateHtMlReport(Date d);
	
	

}
