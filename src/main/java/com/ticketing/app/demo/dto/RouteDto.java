package com.ticketing.app.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketing.app.demo.dto.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDto extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -707136262922442381L;
	@JsonIgnore
	private Long id;
	private String startPoint;
	private String endPoint;
}
