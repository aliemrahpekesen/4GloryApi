package com.glory.service.model;

public class MileCheckResponse {

	private String status;
	private int messageCode;
	private String message;
	private Long amadeusTransactionID;
	private String partnerTransactionCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getAmadeusTransactionID() {
		return amadeusTransactionID;
	}

	public void setAmadeusTransactionID(Long amadeusTransactionID) {
		this.amadeusTransactionID = amadeusTransactionID;
	}

}
