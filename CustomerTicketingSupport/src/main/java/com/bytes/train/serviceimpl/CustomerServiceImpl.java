package com.bytes.train.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Customer;
import com.bytes.train.repos.CustomerDao;
import com.bytes.train.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerDao customerDao;
	@Override
	public List<Customer> getCustomerDetails() {
		return customerDao.findAll();
	}

	@Override
	public void addCustomerDetails(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.save(customer);
	}

	@Override
	public void addTicketDetails() {
		// TODO Auto-generated method stub
		
	}
	

}
