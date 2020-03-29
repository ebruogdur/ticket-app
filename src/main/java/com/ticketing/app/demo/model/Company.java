package com.ticketing.app.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ticketing.app.demo.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "company", schema = "ticketing")
public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6918274093875210409L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private UUID uuid;
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
	@Column(unique = true)
	@NotNull
	private String codeName;
	@NotNull
	@Column(unique = true)
	private String domainName;
	@NotNull
	private String name;
	@NotNull
	private String formalName;
	@NotNull
	private String description;
}
