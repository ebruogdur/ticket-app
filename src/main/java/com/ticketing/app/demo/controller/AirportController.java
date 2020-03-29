package com.ticketing.app.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.app.demo.dto.AirportDto;
import com.ticketing.app.demo.enums.Message;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Airport;
import com.ticketing.app.demo.request.AirportRequest;
import com.ticketing.app.demo.response.AirportResponse;
import com.ticketing.app.demo.service.AirportService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/airport")
public class AirportController {
	@Autowired
	AirportService airportService;
	String message = Message.FAIL.getCode();

	@ApiOperation(value = "Get airport by uuid", notes = "Return airport by uuid")
	@GetMapping(value = "/{uuid}")
	public AirportResponse getAppAirports(@PathVariable("uuid") UUID uuid) {
		AirportDto airportDto = airportService.get(uuid);
		if (airportDto != null) {
			message = Message.SUCCESS.getCode();
		}
		return new AirportResponse(airportDto, message);
	}

	@ApiOperation(value = "Insert airport", notes = "Insert airport ")
	@PostMapping()
	public String saveAppAirports(@RequestBody AirportRequest req) {
		airportService.save(req.getAppAirports());
		if (req.getAppAirports() != null) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Update airport by uuid", notes = "Update airport by uuid")
	@PutMapping(value = "/{uuid}")
	public void updateAppAirports(@RequestBody AirportRequest req, @PathVariable("uuid") UUID uuid) {
		airportService.update(uuid, req.getAppAirports());
	}

	@ApiOperation(value = "Delete airport by uuid", notes = "Delete airport by uuid")
	@DeleteMapping(value = "/{uuid}")
	public String deleteAppAirports(@PathVariable("uuid") UUID uuid) {
		AirportDto airportDto = airportService.get(uuid);
		airportService.delete(uuid);
		if (airportDto.getStatus() == Status.DELETED.getCode()) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Search airport by name", notes = "Search airport by name")
	@PostMapping(value = "/searchbyname")
	public List<Airport> searchAppAirports(@RequestBody String name) {
		return airportService.searchByName(name);
	}
}
