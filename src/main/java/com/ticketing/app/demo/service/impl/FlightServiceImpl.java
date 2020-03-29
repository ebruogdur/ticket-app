package com.ticketing.app.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.app.demo.component.Property;
import com.ticketing.app.demo.dao.FlightDao;
import com.ticketing.app.demo.dao.TicketDao;
import com.ticketing.app.demo.dto.FlightDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Flight;
import com.ticketing.app.demo.model.Ticket;
import com.ticketing.app.demo.service.FlightService;
import com.ticketing.app.demo.util.Mapper;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	Property property;
	@Autowired
	FlightDao appFlightsDao;
	@Autowired
	TicketDao appTicketDao;
	private final Mapper<Flight, FlightDto> mapper = new Mapper<>(Flight.class, FlightDto.class);

	@Override
	public FlightDto get(UUID uuid) {
		return mapper.model2Dto(appFlightsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid));
	}

	@Override
	public void save(FlightDto dto) {
		dto.setUuid(UUID.randomUUID());
		dto.setCompanyId(property.getCompanyId());
		appFlightsDao.save(mapper.dto2Model(dto));
	}

	@Override
	public void update(UUID uuid, FlightDto dto) {
		Flight appFlights = appFlightsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appFlights != null) {
			dto.setId(appFlights.getId());
			dto.setUuid(appFlights.getUuid());
			dto.setCompanyId(appFlights.getCompanyId());
			dto.setCreatedBy(appFlights.getCreatedBy());
			dto.setCreatedTime(appFlights.getCreatedTime());
			appFlightsDao.save(mapper.dto2Model(dto, appFlights));
		}
	}

	@Override
	public void delete(UUID uuid) {
		Flight appFlights = appFlightsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appFlights != null) {
			appFlights.setStatus(Status.DELETED.getCode());
			appFlightsDao.save(appFlights);
		}
	}

	@Override
	public List<Flight> searchByCode(String code) {
		return appFlightsDao.findByCode(code);
	}

	@Override
	public BigDecimal calculateAmount(Long flightId) {
		Flight findByCompanyIdAndId = appFlightsDao.findByCompanyIdAndId(property.getCompanyId(), flightId);
		BigDecimal price = findByCompanyIdAndId.getPrice();
		BigDecimal total = price;
		List<Ticket> findByCompanyIdAndFlightId = appTicketDao.findByCompanyIdAndFlightId(property.getCompanyId(), flightId);
		int size = findByCompanyIdAndFlightId.size();
		int quota = findByCompanyIdAndId.getQuota().intValue();
		for (int i = 10; i <= 100; i += 10) {
			int result = (int) (quota * i) / 100;
			if (size == result) {
				total = price.add(price.multiply(new BigDecimal(10)).divide(new BigDecimal(100)));
				break;
			}
		}
		return total;
	}
}
