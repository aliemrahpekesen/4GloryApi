package com.glory.service.model;

public class BurnMilesResponse {
	
	private String status;
	private int messageCode;
	private String message;
	private Long amadeusTransactionId;
	private String partnerTransactionId;
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
	public Long getAmadeusTransactionId() {
		return amadeusTransactionId;
	}
	public void setAmadeusTransactionId(Long amadeusTransactionId) {
		this.amadeusTransactionId = amadeusTransactionId;
	}
	public String getPartnerTransactionId() {
		return partnerTransactionId;
	}
	public void setPartnerTransactionId(String partnerTransactionId) {
		this.partnerTransactionId = partnerTransactionId;
	}
	
	

}
