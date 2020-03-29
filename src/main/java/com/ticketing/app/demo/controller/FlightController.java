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

import com.ticketing.app.demo.dto.FlightDto;
import com.ticketing.app.demo.enums.Message;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Flight;
import com.ticketing.app.demo.request.FlightRequest;
import com.ticketing.app.demo.response.FlightResponse;
import com.ticketing.app.demo.service.FlightService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	FlightService flightService;
	String message = Message.FAIL.getCode();

	@ApiOperation(value = "Get flight by uuid", notes = "Return flight by uuid ")
	@GetMapping(value = "/{uuid}")
	public FlightResponse getAppFlights(@PathVariable("uuid") UUID uuid) {
		FlightDto flightDto = flightService.get(uuid);
		if (flightDto != null) {
			message = Message.SUCCESS.getCode();
		}
		return new FlightResponse(flightDto, message);
	}

	@ApiOperation(value = "Insert flight", notes = "Insert flight ")
	@PostMapping()
	public String saveAppFlights(@RequestBody FlightRequest req) {
		flightService.save(req.getAppFlights());
		if (req.getAppFlights() != null) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Update flight by uuid", notes = "Update flight by uuid")
	@PutMapping(value = "/{uuid}")
	public void updateAppFlights(@RequestBody FlightRequest req, @PathVariable("uuid") UUID uuid) {
		flightService.update(uuid, req.getAppFlights());
	}

	@ApiOperation(value = "Delete flight by uuid", notes = "Delete flight by uuid")
	@DeleteMapping(value = "/{uuid}")
	public String deleteAppFlights(@PathVariable("uuid") UUID uuid) {
		FlightDto flightDto = flightService.get(uuid);
		flightService.delete(uuid);
		if (flightDto.getStatus() == Status.DELETED.getCode()) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Search flight by code", notes = "Search flight by code")
	@PostMapping(value = "/searchbycode")
	public List<Flight> searchAppFlights(@RequestBody String code) {
		return flightService.searchByCode(code);
	}
}
