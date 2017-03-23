package com.glory.model.enums;

public enum Currency {
	USD("USD"), EUR("EUR"), TRY("TRY");

	private final String code;

	private Currency(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}

}
