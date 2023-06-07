package com.bytes.train.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytes.train.entities.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer> {
  
}
