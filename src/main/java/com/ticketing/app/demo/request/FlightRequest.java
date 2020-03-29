package com.ticketing.app.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.FlightDto;
import com.ticketing.app.demo.request.base.BaseRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightRequest implements BaseRequest<FlightDto> {
	private static final long serialVersionUID = 8169827426318916655L;
	@JsonProperty("flight")
	private FlightDto appFlights;

	@Override
	public FlightDto get() {
		return appFlights;
	}
}
