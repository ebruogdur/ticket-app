package com.ticketing.app.demo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketing.app.demo.model.Ticket;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {
	Ticket findByCompanyIdAndUuid(Long companyId, UUID uuid);

	List<Ticket> findByCompanyIdAndFlightId(Long companyId, Long flightId);

	Ticket findByCompanyIdAndTicketNumber(Long companyId, String ticketNumber);

	List<Ticket> findByCompanyId(Long companyId);
}
