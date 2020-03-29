package com.ticketing.app.demo.controller;

import java.math.BigDecimal;
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

import com.ticketing.app.demo.dto.TicketDto;
import com.ticketing.app.demo.enums.Message;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Ticket;
import com.ticketing.app.demo.request.TicketRequest;
import com.ticketing.app.demo.response.TicketResponse;
import com.ticketing.app.demo.service.FlightService;
import com.ticketing.app.demo.service.TicketService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService;
	@Autowired
	FlightService flightService;
	String message = Message.FAIL.getCode();

	@ApiOperation(value = "Get ticket by uuid", notes = "Return ticket by uuid ")
	@GetMapping(value = "/{uuid}")
	public TicketResponse getAppTickets(@PathVariable("uuid") UUID uuid) {
		TicketDto ticketDto = ticketService.get(uuid);
		if (ticketDto != null) {
			message = Message.SUCCESS.getCode();
		}
		return new TicketResponse(ticketDto, message);
	}

	@ApiOperation(value = "Buy ticket", notes = "Buy ticket")
	@PostMapping()
	public String saveAppTickets(@RequestBody TicketRequest req) {
		BigDecimal calculateAmount = flightService.calculateAmount(req.getAppTickets().getFlightId());
		if (calculateAmount != null) {
			req.getAppTickets().setAmount(calculateAmount);
		}
		ticketService.save(req.getAppTickets());
		if (req.getAppTickets() != null) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Update ticket by uuid", notes = "Update ticket by uuid ")
	@PutMapping(value = "/{uuid}")
	public void updateAppTickets(@RequestBody TicketRequest req, @PathVariable("uuid") UUID uuid) {
		ticketService.update(uuid, req.getAppTickets());
	}

	@ApiOperation(value = "Delete ticket by uuid", notes = "Delete ticket by uuid ")
	@DeleteMapping(value = "/{uuid}")
	public String deleteAppTickets(@PathVariable("uuid") UUID uuid) {
		TicketDto ticketDto = ticketService.get(uuid);
		ticketService.delete(uuid);
		if (ticketDto.getStatus() == Status.DELETED.getCode()) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Search ticket by ticket number", notes = "Search ticket by ticket number")
	@PostMapping(value = "/searchbyticketnumber")
	public Ticket searchAppTickets(@RequestBody String ticketNumber) {
		return ticketService.searchByTicketNumber(ticketNumber);
	}

	@ApiOperation(value = "Cancel ticket by ticket number", notes = "Cancel ticket by ticket number")
	@PostMapping(value = "/cancel")
	public String cancelAppTickets(@RequestBody String ticketNumber) {
		Ticket searchByTicketNumber = ticketService.searchByTicketNumber(ticketNumber);
		ticketService.cancelByTicketNumber(ticketNumber);
		if (searchByTicketNumber.getStatus() == Status.PASSIVE.getCode()) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}
}
