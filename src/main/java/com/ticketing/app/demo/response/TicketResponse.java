package com.ticketing.app.demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.TicketDto;
import com.ticketing.app.demo.response.base.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketResponse implements BaseResponse {
	private static final long serialVersionUID = -4845185564920399602L;

	public TicketResponse(TicketDto appTickets, String message) {
		this.appTickets = appTickets;
		this.message = message;
	}

	@JsonProperty("ticket")
	@JsonInclude(Include.NON_NULL)
	private TicketDto appTickets;

	public TicketResponse(List<TicketDto> appTicketsList) {
		this.appTicketsList = appTicketsList;
	}

	@JsonProperty("tickets")
	@JsonInclude(Include.NON_NULL)
	private List<TicketDto> appTicketsList;
	private String message;
}
