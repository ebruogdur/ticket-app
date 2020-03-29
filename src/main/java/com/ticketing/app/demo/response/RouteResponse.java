package com.ticketing.app.demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.RouteDto;
import com.ticketing.app.demo.response.base.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RouteResponse implements BaseResponse {
	private static final long serialVersionUID = -4845185564920399602L;

	public RouteResponse(RouteDto appRoutes, String message) {
		this.appRoutes = appRoutes;
		this.message = message;
	}

	@JsonProperty("route")
	@JsonInclude(Include.NON_NULL)
	private RouteDto appRoutes;

	public RouteResponse(List<RouteDto> appRoutesList) {
		this.appRoutesList = appRoutesList;
	}

	@JsonProperty("routes")
	@JsonInclude(Include.NON_NULL)
	private List<RouteDto> appRoutesList;
	private String message;
}
