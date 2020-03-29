package com.ticketing.app.demo.dto.base;

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
public abstract class BaseDto implements Serializable {
	private static final long serialVersionUID = 436853142306266121L;
	private UUID uuid;
	@JsonIgnore
	private Long companyId;
	private Long createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date createdTime;
	private Long lastUpdatedBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date lastUpdatedTime;
	private int status = Status.ACTIVE.getCode();
}
