package com.ticketing.app.demo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketing.app.demo.dto.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3053792540129805955L;
	@JsonIgnore
	private Long id;
	private String ticketNumber;
	private Long flightId;
	private String passengerName;
	private String passengerSurname;
	private String passengerIdentityCardNo;
	private String cabin;
	private String seatNo;
	private String gateNo;
	private BigDecimal amount;
	private String currency;
	private BigDecimal vat;
}
