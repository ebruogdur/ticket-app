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
@Table(name = "route", schema = "ticketing")
public class Route extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6918274093875210409L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String startPoint;
	@NotNull
	private String endPoint;
}
