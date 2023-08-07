package com.bytes.train.serviceimpl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	TicketRepository ticketRepository;

	@Override
	public Customer checkAccess(String userName, String password) {
		Customer userdata = customerRepository.findByUserName(userName);
		if ((userdata.getUserName().equals(userName)) && (userdata.getUserPassword().equals(password))) {
			return userdata;
		}
		return null;

	}

	@Override
	public List<Ticket> getTicketDetails(int id) {
		return ticketRepository.findByCustomer_id(id);
	}

	@Override
	public String setTicketSatisfactoryRating(int ticketId, Float rating) {
		Ticket singleTicket = ticketRepository.findById(ticketId).orElseThrow();
		if (singleTicket.getStatus().equalsIgnoreCase("Closed") && singleTicket.getSatisfactionRating() == 0) {
			singleTicket.setSatisfactionRating(rating);
			ticketRepository.save(singleTicket);
			return "Ticket Rating is Being Sucessfully Added";
		} else {
			return "Rating Has  Being Added Already";
		}
	}

	// Customer Open and Close Tickets
	@Override
	public List<Ticket> getFilteredCustomerTickets(int cutsomerId, String status) {
		System.out.println(cutsomerId);
		List<Ticket> filterTickets = ticketRepository.findByCustomer_idAndStatus(cutsomerId, status);
		return filterTickets;
	}


//	Customer Search
	@Override
	public List<Ticket> getSearch(int agentId, SearchCriteria searchCriteria) {
		Customer agent = customerRepository.findById(agentId).orElseThrow();
//		List<Category> category = agent.getCategory();
		if (searchCriteria.getStatus().equalsIgnoreCase("Open")) {
			String serachStatus = searchCriteria.getStatus();
			List<Ticket> searchResult = new LinkedList<>();
			List<Ticket> custTickets=ticketRepository.findByStatus(serachStatus);
//			List<Ticket> tickets = ticketRepository.findAllByCategoryIdInAndStatus(category, serachStatus);
			System.out.println(custTickets);
			if (isNumeric(searchCriteria.getSubject())) {
				int ticketId = Integer.parseInt(searchCriteria.getSubject());
				for (Ticket ticket : custTickets) {
					if (ticket.getTicketId() == ticketId) {
						searchResult.add(ticket);
						return searchResult;
					}
				}
			} else {
				for (Ticket ticket : custTickets) {
					if (ticket.getSubject().contains(searchCriteria.getSubject())) {
						searchResult.add(ticket);
					}
				}
			}

			return searchResult;

		} else if (searchCriteria.getStatus().equalsIgnoreCase("closed")) {
			List<Ticket> searchResult = new LinkedList<>();
			String searchStatus=searchCriteria.getStatus();
			List<Ticket> custTickets=ticketRepository.findByStatus(searchStatus);
			if (isNumeric(searchCriteria.getSubject())) {
				int ticketId = Integer.parseInt(searchCriteria.getSubject());
				for (Ticket ticket : custTickets) {
					if (ticket.getTicketId() == ticketId) {
						searchResult.add(ticket);
						return searchResult;
					}
				}
			} else {
				for (Ticket ticket : custTickets) {
					if (ticket.getSubject().contains(searchCriteria.getSubject())) {
						searchResult.add(ticket);
					}
				}

			}

			return searchResult;

		} else {
			System.out.println("Here");
			
			Customer agent1 = customerRepository.findById(agentId).orElseThrow();
//			Agent agents = agentRepository.findById(agentId).orElse(null);
//			List<Category> categorys = agent.getCategory();
			List<Ticket> tickets = ticketRepository.findAll();

			String search;
			search = searchCriteria.getSubject();

			List<Ticket> searchResult = new LinkedList<>();

			for (Ticket ticket : tickets) {
				if (isNumeric(search)) {
					int ticketId = Integer.parseInt(search);
					if (ticket.getTicketId() == ticketId) {
						searchResult.add(ticket);
						return searchResult;
					}
				} else {
					if ((ticket.getSubject().toLowerCase()).contains((searchCriteria.getSubject()).toLowerCase())) {
						searchResult.add(ticket);
					}
				}

			}
			return searchResult;

		}
	}

	private boolean isNumeric(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	
//	@Override
//	public List<Ticket> getClosedCustomerTickets(int customerId) {
//		List<Ticket> closeTickets=ticketRepository.findByCustomer_customeridAndStatus(customerId,"Closed");
//		return closeTickets;
//	}

}
