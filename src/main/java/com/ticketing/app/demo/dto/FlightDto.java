package com.ticketing.app.demo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketing.app.demo.dto.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDto extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8948163394805572478L;
	@JsonIgnore
	private Long id;
	private String code;
	private Long fromAirportId;
	private Long toAirportId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date flightDate;
	private Long quota;
	private Integer flightDurationInMinutes;
	private Long routeId;
	private BigDecimal price;
}
