package com.bytes.train.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.dto.CustomerDto;
import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerDao;
	@Autowired
	TicketRepository ticketDao;

	@Override
	public List<Customer> getCustomerDetails() {
		return customerDao.findAll();
	}

	@Override
	public Customer addCustomerDetails(Customer customer) {
		return customerDao.save(customer);
	}

	@Override
	public List<Ticket> getTicketDetails(int id) {
		// TODO Auto-generated method stub
		return ticketDao.findByCustomerId(id);
	}

	public List<CustomerDto> getCustomerDto(int id) {

		return ticketDao.findByCustomerId(id).stream().map(this::convertEntityToDto).collect(Collectors.toList());

	}

	public CustomerDto convertEntityToDto(Ticket ticket) {
		CustomerDto customerDto = new CustomerDto();
	
		customerDto.setTicketId(ticket.getTicketId());
		customerDto.setSubject(ticket.getSubject());
		customerDto.setDescription(ticket.getDescription());
		customerDto.setStatus(ticket.getStatus());
		return customerDto;

	}

}
