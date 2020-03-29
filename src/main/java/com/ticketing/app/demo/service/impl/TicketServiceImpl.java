package com.ticketing.app.demo.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.app.demo.component.Property;
import com.ticketing.app.demo.dao.TicketDao;
import com.ticketing.app.demo.dto.TicketDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Ticket;
import com.ticketing.app.demo.service.TicketService;
import com.ticketing.app.demo.util.Mapper;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	@Autowired
	Property property;
	@Autowired
	TicketDao appTicketsDao;
	private final Mapper<Ticket, TicketDto> mapper = new Mapper<>(Ticket.class, TicketDto.class);

	@Override
	public TicketDto get(UUID uuid) {
		return mapper.model2Dto(appTicketsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid));
	}

	@Override
	public void save(TicketDto dto) {
		dto.setUuid(UUID.randomUUID());
		dto.setCompanyId(property.getCompanyId());
		appTicketsDao.save(mapper.dto2Model(dto));
	}

	@Override
	public void update(UUID uuid, TicketDto dto) {
		Ticket appTickets = appTicketsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appTickets != null) {
			dto.setId(appTickets.getId());
			dto.setUuid(appTickets.getUuid());
			dto.setCompanyId(appTickets.getCompanyId());
			dto.setCreatedBy(appTickets.getCreatedBy());
			dto.setCreatedTime(appTickets.getCreatedTime());
			appTicketsDao.save(mapper.dto2Model(dto, appTickets));
		}
	}

	@Override
	public void delete(UUID uuid) {
		Ticket appTickets = appTicketsDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appTickets != null) {
			appTickets.setStatus(Status.DELETED.getCode());
			appTicketsDao.save(appTickets);
		}
	}

	@Override
	public Ticket searchByTicketNumber(String ticketNumber) {
		return appTicketsDao.findByCompanyIdAndTicketNumber(property.getCompanyId(), ticketNumber);
	}

	@Override
	public void cancelByTicketNumber(String ticketNumber) {
		Ticket searchByTicketNumber = searchByTicketNumber(ticketNumber);
		if (searchByTicketNumber != null) {
			searchByTicketNumber.setStatus(Status.PASSIVE.getCode());
			appTicketsDao.save(searchByTicketNumber);
		}
	}
}
