package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.CustomerDao;
import com.bytes.train.repos.TicketDao;
import com.bytes.train.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerDao customerDao;
	@Autowired
	TicketDao ticketDao;

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
	
	
	

}
