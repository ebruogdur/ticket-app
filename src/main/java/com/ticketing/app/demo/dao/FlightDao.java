package com.ticketing.app.demo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketing.app.demo.model.Flight;

@Repository
public interface FlightDao extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
	Flight findByCompanyIdAndUuid(Long companyId, UUID uuid);

	Flight findByCompanyIdAndId(Long companyId, Long id);

	List<Flight> findByCode(String code);

	List<Flight> findByCompanyId(Long companyId);
}
