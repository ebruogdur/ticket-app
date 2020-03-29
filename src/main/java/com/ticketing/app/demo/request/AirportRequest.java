package com.ticketing.app.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.AirportDto;
import com.ticketing.app.demo.request.base.BaseRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirportRequest implements BaseRequest<AirportDto> {
	private static final long serialVersionUID = 8169827426318916655L;
	@JsonProperty("airport")
	private AirportDto appAirports;

	@Override
	public AirportDto get() {
		return appAirports;
	}
}
