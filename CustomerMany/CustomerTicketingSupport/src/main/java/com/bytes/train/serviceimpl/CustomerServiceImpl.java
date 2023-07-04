package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Customer;
import com.bytes.train.entities.Ticket;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.repos.TicketRepository;
import com.bytes.train.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	TicketRepository ticketDao;

	@Override
	public Customer checkAccess(String userName, String password) {
		Customer userdata = customerRepository.findByUsername(userName);
		if ((userdata.getUsername().equals(userName)) && (userdata.getUserpassword().equals(password))) {
			return userdata;
		}
		return null;

	}
	@Override
	public List<Ticket> getTicketDetails(int id) {
		// TODO Auto-generated method stub
		return ticketDao.findByCustomerId(id);
	}

}
