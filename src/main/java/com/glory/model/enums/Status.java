package com.glory.model.enums;

public enum Status {
	INITIALIZED("INIT"), BLOCKED("BLK"), SPENT("SPNT"), ERROR("ERR");

	private final String code;

	private Status(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}

}
