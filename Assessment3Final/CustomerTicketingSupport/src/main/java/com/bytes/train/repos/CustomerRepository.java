package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByUserName(String username);

}
