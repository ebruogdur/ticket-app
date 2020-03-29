package com.ticketing.app.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.ticketing.app.demo.dto.FlightDto;
import com.ticketing.app.demo.model.Flight;
import com.ticketing.app.demo.service.base.BaseService;

public interface FlightService extends BaseService<FlightDto> {
	List<Flight> searchByCode(String code);

	BigDecimal calculateAmount(Long flightId);
}
