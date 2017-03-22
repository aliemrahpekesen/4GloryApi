package com.glory.service.responses;

public class CheckMilesResponse {

	private String partnerTransactionCode;
	private String amadeusTransactionCode;
	private String status;
	private int messageCode;
	private String message;

	public String getPartnerTransactionCode() {
		return partnerTransactionCode;
	}

	public void setPartnerTransactionCode(String partnerTransactionCode) {
		this.partnerTransactionCode = partnerTransactionCode;
	}

	public String getAmadeusTransactionCode() {
		return amadeusTransactionCode;
	}

	public void setAmadeusTransactionCode(String amadeusTransactionCode) {
		this.amadeusTransactionCode = amadeusTransactionCode;
	}

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

}
