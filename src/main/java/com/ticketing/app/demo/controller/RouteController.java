package com.ticketing.app.demo.controller;

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

import com.ticketing.app.demo.dto.RouteDto;
import com.ticketing.app.demo.enums.Message;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.request.RouteRequest;
import com.ticketing.app.demo.response.RouteResponse;
import com.ticketing.app.demo.service.RouteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/route")
public class RouteController {
	@Autowired
	RouteService routeService;
	String message = Message.FAIL.getCode();

	@ApiOperation(value = "Get route by uuid", notes = "Return route by uuid ")
	@GetMapping(value = "/{uuid}")
	public RouteResponse getAppRoutes(@PathVariable("uuid") UUID uuid) {
		RouteDto routeDto = routeService.get(uuid);
		if (routeDto != null) {
			message = Message.SUCCESS.getCode();
		}
		return new RouteResponse(routeDto, message);
	}

	@ApiOperation(value = "Insert route", notes = "Insert route ")
	@PostMapping()
	public String saveAppRoutes(@RequestBody RouteRequest req) {
		routeService.save(req.getAppRoutes());
		if (req.getAppRoutes() != null) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Update route by uuid", notes = "Update route by uuid")
	@PutMapping(value = "/{uuid}")
	public void updateAppRoutes(@RequestBody RouteRequest req, @PathVariable("uuid") UUID uuid) {
		routeService.update(uuid, req.getAppRoutes());
	}

	@ApiOperation(value = "Delete route by uuid", notes = "Delete route by uuid")
	@DeleteMapping(value = "/{uuid}")
	public String deleteAppRoutes(@PathVariable("uuid") UUID uuid) {
		RouteDto routeDto = routeService.get(uuid);
		routeService.delete(uuid);
		if (routeDto.getStatus() == Status.DELETED.getCode()) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}
}
