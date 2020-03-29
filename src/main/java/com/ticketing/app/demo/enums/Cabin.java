package com.ticketing.app.demo.enums;

public enum Cabin {
	ECONOMY("ECO"), BUSINESS("BUS");

	private final String code;

	Cabin(final String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
}
