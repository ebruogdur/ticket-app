package com.ticketing.app.demo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketing.app.demo.component.Property;
import com.ticketing.app.demo.dao.CompanyDao;
import com.ticketing.app.demo.dto.CompanyDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Company;
import com.ticketing.app.demo.service.CompanyService;
import com.ticketing.app.demo.util.Mapper;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	Property property;
	@Autowired
	CompanyDao appCompaniesDao;
	private final Mapper<Company, CompanyDto> mapper = new Mapper<>(Company.class, CompanyDto.class);

	@Override
	public CompanyDto get(UUID uuid) {
		return mapper.model2Dto(appCompaniesDao.findByUuid(uuid));
	}

	@Override
	public void save(CompanyDto dto) {
		dto.setUuid(UUID.randomUUID());
		appCompaniesDao.save(mapper.dto2Model(dto));
	}

	@Override
	public void update(UUID uuid, CompanyDto dto) {
		Company appCompanies = appCompaniesDao.findByUuid(uuid);
		if (appCompanies != null) {
			dto.setId(appCompanies.getId());
			dto.setUuid(appCompanies.getUuid());
			dto.setCreatedBy(appCompanies.getCreatedBy());
			dto.setCreatedTime(appCompanies.getCreatedTime());
			appCompaniesDao.save(mapper.dto2Model(dto, appCompanies));
		}
	}

	@Override
	public void delete(UUID uuid) {
		Company appCompanies = appCompaniesDao.findByUuid(uuid);
		if (appCompanies != null) {
			appCompanies.setStatus(Status.DELETED.getCode());
			appCompaniesDao.save(appCompanies);
		}
	}

	@Override
	public List<Company> searchByName(String name) {
		return appCompaniesDao.findByNameIgnoreCaseContaining(name);
	}
}
