package com.ticketing.app.demo.model.base;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.ticketing.app.demo.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5287922088035494786L;
	@NotNull
	private UUID uuid;
	@NotNull
	private Long companyId;
	@NotNull
	private Long createdBy;
	@NotNull
	private Date createdTime;
	@NotNull
	private Long lastUpdatedBy;
	@NotNull
	private Date lastUpdatedTime;
	@NotNull
	private int status = Status.ACTIVE.getCode();
}
