package com.ticketing.app.demo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketing.app.demo.model.Company;

@Repository
public interface CompanyDao extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
	Company findByUuid(UUID uuid);

	List<Company> findByNameIgnoreCaseContaining(String name);
}
