package com.ticketing.app.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketing.app.demo.dto.base.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportDto extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4070486667679665882L;
	@JsonIgnore
	private Long id;
	private String address;
	private String code;
	private Integer gateCount;
	private String city;
	private String country;
	private String name;
}
