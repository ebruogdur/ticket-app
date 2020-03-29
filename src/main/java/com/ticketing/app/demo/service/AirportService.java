package com.ticketing.app.demo.service;

import java.util.List;

import com.ticketing.app.demo.dto.AirportDto;
import com.ticketing.app.demo.model.Airport;
import com.ticketing.app.demo.service.base.BaseService;

public interface AirportService extends BaseService<AirportDto> {
	List<Airport> searchByName(String name);
}
