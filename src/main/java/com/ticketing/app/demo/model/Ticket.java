package com.ticketing.app.demo.model;

import java.math.BigDecimal;

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
@Table(name = "ticket", schema = "ticketing")
public class Ticket extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6918274093875210409L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String ticketNumber;
	@NotNull
	private Long flightId;
	@NotNull
	private String passengerName;
	@NotNull
	private String passengerSurname;
	@NotNull
	private String passengerIdentityCardNo;
	@NotNull
	private String cabin;
	@NotNull
	private String seatNo;
	@NotNull
	private String gateNo;
	@NotNull
	private BigDecimal amount;
	@NotNull
	private String currency;
	@NotNull
	private BigDecimal vat;
}
