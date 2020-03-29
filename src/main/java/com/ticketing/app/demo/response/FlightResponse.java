package com.ticketing.app.demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.FlightDto;
import com.ticketing.app.demo.response.base.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightResponse implements BaseResponse {
	private static final long serialVersionUID = -4845185564920399602L;

	public FlightResponse(FlightDto appFlights, String message) {
		this.appFlights = appFlights;
		this.message = message;
	}

	@JsonProperty("flight")
	@JsonInclude(Include.NON_NULL)
	private FlightDto appFlights;

	public FlightResponse(List<FlightDto> appFlightsList) {
		this.appFlightsList = appFlightsList;
	}

	@JsonProperty("flights")
	@JsonInclude(Include.NON_NULL)
	private List<FlightDto> appFlightsList;
	private String message;
}
