package com.ticketing.app.demo.model;

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
@Table(name = "user", schema = "ticketing")
public class User extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6918274093875210409L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String userName;
	@NotNull
	private String userEmail;
	@NotNull
	private String passwordHashSha512;
	private Date lastLoginTime;
	private Date lastPasswordUpdateTime;
	private String languageCode;
	private String timezone;
	@NotNull
	private String fullName;
}
