package com.ticketing.app.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketing.app.demo.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9136322589353979871L;
	@JsonIgnore
	private Long id;
	private UUID uuid;
	private String codeName;
	private String name;
	private String description;
	private Long createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date createdTime;
	private Long lastUpdatedBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date lastUpdatedTime;
	private int status = Status.ACTIVE.getCode();
	private String domainName;
	private String formalName;
}
