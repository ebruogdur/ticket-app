package com.ticketing.app.demo.service;

import java.util.List;

import com.ticketing.app.demo.dto.CompanyDto;
import com.ticketing.app.demo.model.Company;
import com.ticketing.app.demo.service.base.BaseService;

public interface CompanyService extends BaseService<CompanyDto> {
	List<Company> searchByName(String name);
}
