package com.bytes.train.serviceimpl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.SearchCriteria;
import com.bytes.train.entities.Ticket;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> getTicketDetails(int id) throws Exception {
		Customer customer = customerRepository.findById(id).orElse(null);
		if (customer == null) {
			throw new ResourceNotFoundException("There Exists No Customer");
		}
		return ticketRepository.findByCustomer(customer);
	}

	@Override
	public String setTicketSatisfactoryRating(int ticketId, Float rating) throws Exception {
		Ticket singleTicket = ticketRepository.findById(ticketId).orElse(null);
		if (singleTicket == null) {
			throw new ResourceNotFoundException("No Ticket Is Found");
		}
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
	public List<Ticket> getFilteredCustomerTickets(int cutsomerId, String status) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(cutsomerId).orElse(null);
		if (customer == null) {
			throw new ResourceNotFoundException("There Exist No Customer");
		}
		List<Ticket> filterTickets = ticketRepository.findByCustomer_idAndStatus(cutsomerId, status);
		return filterTickets;
	}

//	Customer Search
	@Override
	public List<Ticket> getSearch(int customerId, SearchCriteria searchCriteria) throws Exception {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			throw new ResourceNotFoundException("No Customer Is Found");
		}
		if (searchCriteria.getStatus().equalsIgnoreCase("Open") || searchCriteria.getStatus().equalsIgnoreCase("Closed")
				|| searchCriteria.getStatus().equalsIgnoreCase("Assigned")) {
			String searchStatus = searchCriteria.getStatus();
			List<Ticket> searchResult = new LinkedList<>();
			List<Ticket> custTickets = ticketRepository.findByStatusAndCustomer(searchStatus, customer);
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
					if ((ticket.getSubject().toLowerCase()).contains((searchCriteria.getSubject().toLowerCase()))) {
						searchResult.add(ticket);
					}
				}
			}

			return searchResult;

		} else {
			System.out.println(searchCriteria.getSubject());
			List<Ticket> tickets = ticketRepository.findByCustomer(customer);
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

}
