package com.ticketing.app.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.RouteDto;
import com.ticketing.app.demo.request.base.BaseRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RouteRequest implements BaseRequest<RouteDto> {
	private static final long serialVersionUID = 8169827426318916655L;
	@JsonProperty("route")
	private RouteDto appRoutes;

	@Override
	public RouteDto get() {
		return appRoutes;
	}
}
