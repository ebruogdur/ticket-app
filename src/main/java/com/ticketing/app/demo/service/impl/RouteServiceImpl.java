package com.ticketing.app.demo.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.app.demo.component.Property;
import com.ticketing.app.demo.dao.RouteDao;
import com.ticketing.app.demo.dto.RouteDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Route;
import com.ticketing.app.demo.service.RouteService;
import com.ticketing.app.demo.util.Mapper;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {
	@Autowired
	Property property;
	@Autowired
	RouteDao appRoutesDao;
	private final Mapper<Route, RouteDto> mapper = new Mapper<>(Route.class, RouteDto.class);

	@Override
	public RouteDto get(UUID uuid) {
		return mapper.model2Dto(appRoutesDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid));
	}

	@Override
	public void save(RouteDto dto) {
		dto.setUuid(UUID.randomUUID());
		dto.setCompanyId(property.getCompanyId());
		appRoutesDao.save(mapper.dto2Model(dto));
	}

	@Override
	public void update(UUID uuid, RouteDto dto) {
		Route appRoutes = appRoutesDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appRoutes != null) {
			dto.setId(appRoutes.getId());
			dto.setUuid(appRoutes.getUuid());
			dto.setCompanyId(appRoutes.getCompanyId());
			dto.setCreatedBy(appRoutes.getCreatedBy());
			dto.setCreatedTime(appRoutes.getCreatedTime());
			appRoutesDao.save(mapper.dto2Model(dto, appRoutes));
		}
	}

	@Override
	public void delete(UUID uuid) {
		Route appRoutes = appRoutesDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appRoutes != null) {
			appRoutes.setStatus(Status.DELETED.getCode());
			appRoutesDao.save(appRoutes);
		}
	}
}
