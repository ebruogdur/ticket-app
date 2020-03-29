package com.ticketing.app.demo.enums;

public enum Message {
	SUCCESS("SUCCESS"), FAIL("FAIL");
	private final String code;

	Message(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
