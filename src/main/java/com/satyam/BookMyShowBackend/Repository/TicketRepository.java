package com.satyam.BookMyShowBackend.Repository;

import com.satyam.BookMyShowBackend.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
