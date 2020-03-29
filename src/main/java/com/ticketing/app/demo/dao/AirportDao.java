package com.ticketing.app.demo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketing.app.demo.model.Airport;

@Repository
public interface AirportDao extends JpaRepository<Airport, Long>, JpaSpecificationExecutor<Airport> {
	Airport findByCompanyIdAndUuid(Long companyId, UUID uuid);

	List<Airport> findByCompanyId(Long companyId);

	List<Airport> findByNameIgnoreCaseContaining(String name);
}
