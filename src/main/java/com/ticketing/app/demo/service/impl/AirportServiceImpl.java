package com.ticketing.app.demo.service.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.app.demo.component.Property;
import com.ticketing.app.demo.dao.AirportDao;
import com.ticketing.app.demo.dto.AirportDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Airport;
import com.ticketing.app.demo.service.AirportService;
import com.ticketing.app.demo.util.Mapper;

@Service
@Transactional
public class AirportServiceImpl implements AirportService {
	@Autowired
	Property property;
	@Autowired
	AirportDao appAirportsDao;
	private final Mapper<Airport, AirportDto> mapper = new Mapper<>(Airport.class, AirportDto.class);

	@Override
	public AirportDto get(UUID uuid) {
		return mapper.model2Dto(appAirportsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid));
	}

	@Override
	public void save(AirportDto dto) {
		dto.setUuid(UUID.randomUUID());
		dto.setCompanyId(property.getCompanyId());
		appAirportsDao.save(mapper.dto2Model(dto));
	}

	@Override
	public void update(UUID uuid, AirportDto dto) {
		Airport appAirports = appAirportsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appAirports != null) {
			dto.setId(appAirports.getId());
			dto.setUuid(appAirports.getUuid());
			dto.setCompanyId(appAirports.getCompanyId());
			dto.setCreatedBy(appAirports.getCreatedBy());
			dto.setCreatedTime(appAirports.getCreatedTime());
			appAirportsDao.save(mapper.dto2Model(dto, appAirports));
		}
	}

	@Override
	public void delete(UUID uuid) {
		Airport appAirports = appAirportsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appAirports != null) {
			appAirports.setStatus(Status.DELETED.getCode());
			appAirportsDao.save(appAirports);
		}
	}

	@Override
	public List<Airport> searchByName(String name) {
		return appAirportsDao.findByNameIgnoreCaseContaining(name);
	}
}
