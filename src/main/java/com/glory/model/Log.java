package com.glory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Log {

	@Id
	@GeneratedValue
	private Long id;

	private Long transactionId;
	private String message;

	public Log() {
	}

	public Log(Long id, Long transactionId, String message) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.message = message;
	}

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
