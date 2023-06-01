package com.bytes.train.repos;



import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Ticket;

public interface TicketDao extends JpaRepository<Ticket,Integer> {

}
