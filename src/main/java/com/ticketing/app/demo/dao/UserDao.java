package com.ticketing.app.demo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketing.app.demo.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	User findByCompanyIdAndUuid(Long companyId, UUID uuid);

	List<User> findByCompanyId(Long companyId);
}
