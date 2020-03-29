package com.ticketing.app.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.TicketDto;
import com.ticketing.app.demo.request.base.BaseRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketRequest implements BaseRequest<TicketDto> {
	private static final long serialVersionUID = 8169827426318916655L;
	@JsonProperty("ticket")
	private TicketDto appTickets;

	@Override
	public TicketDto get() {
		return appTickets;
	}
}
