package com.ticketing.app.demo.model;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "flight", schema = "ticketing")
public class Flight extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6918274093875210409L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String code;
	@NotNull
	private Long fromAirportId;
	@NotNull
	private Long toAirportId;
	@NotNull
	private Date flightDate;
	@NotNull
	private Long quota;
	@NotNull
	private Integer flightDurationInMinutes;
	@NotNull
	private Long routeId;
	@NotNull
	private BigDecimal price;
}
