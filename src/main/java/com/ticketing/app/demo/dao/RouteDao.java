package com.ticketing.app.demo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketing.app.demo.model.Route;

@Repository
public interface RouteDao extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
	Route findByCompanyIdAndUuid(Long companyId, UUID uuid);

	List<Route> findByCompanyId(Long companyId);
}
