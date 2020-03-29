package com.ticketing.app.demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.AirportDto;
import com.ticketing.app.demo.response.base.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirportResponse implements BaseResponse {
	private static final long serialVersionUID = -4845185564920399602L;

	public AirportResponse(AirportDto appAirports,String message) {
		this.appAirports = appAirports;
		this.message=message;
	}
	@JsonProperty("airport")
	@JsonInclude(Include.NON_NULL)
	private AirportDto appAirports;

	public AirportResponse(List<AirportDto> appAirportsList) {
		this.appAirportsList = appAirportsList;
	}

	@JsonProperty("airports")
	@JsonInclude(Include.NON_NULL)
	private List<AirportDto> appAirportsList;
	
	private String message;
}
