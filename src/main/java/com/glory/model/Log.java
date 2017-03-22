package com.glory.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Log {

	@Id
	@GeneratedValue
	private Long id;

	private Long transactionId;
	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
