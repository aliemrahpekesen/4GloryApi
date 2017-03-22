package com.glory.model.enums;

public enum Currency {
	USD("USD"), EURO("EU"), TL("TL");

	private final String code;

	private Currency(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}

}
