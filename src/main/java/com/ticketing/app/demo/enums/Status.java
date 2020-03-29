package com.ticketing.app.demo.enums;

public enum Status {
	ACTIVE(1), PASSIVE(2), DELETED(9);

	private final int code;

	Status(final int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
