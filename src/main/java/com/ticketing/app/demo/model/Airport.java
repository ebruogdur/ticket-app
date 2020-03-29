package com.ticketing.app.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ticketing.app.demo.model.base.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "airport", schema = "ticketing")
public class Airport extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2908865112512320381L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String address;
	@NotNull
	private String code;
	@NotNull
	private Integer gateCount;
	@NotNull
	private String city;
	@NotNull
	private String country;
	@NotNull
	private String name;
}
