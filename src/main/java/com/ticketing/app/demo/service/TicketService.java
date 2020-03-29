package com.ticketing.app.demo.service;

import com.ticketing.app.demo.dto.TicketDto;
import com.ticketing.app.demo.model.Ticket;
import com.ticketing.app.demo.service.base.BaseService;

public interface TicketService extends BaseService<TicketDto> {
	Ticket searchByTicketNumber(String ticketNumber);

	void cancelByTicketNumber(String ticketNumber);
}
