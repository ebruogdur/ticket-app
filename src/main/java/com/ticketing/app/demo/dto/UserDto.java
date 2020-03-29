package com.ticketing.app.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ticketing.app.demo.dto.base.BaseDto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDto extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8951616973963228335L;
	@JsonIgnore
	private Long id;
	private String userName;
	private String userEmail;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String passwordHashSha512;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date lastLoginTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date lastPasswordUpdateTime;
	private String languageCode;
	private String timezone;
	private String fullName;
}
