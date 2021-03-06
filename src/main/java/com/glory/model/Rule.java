package com.glory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.glory.model.enums.Currency;

@Entity
public class Rule {

	@Id
	@GeneratedValue
	private Long id;

	private String code;
	private Double rate;
	private Currency currency;
	private String description;
	private Long partnerId;
	private Long airlineId;

	public Rule() {
	}

	public Rule(Long id, String code, Double rate, Currency currency, String description, Long partnerId,
			Long airlineId) {
		super();
		this.id = id;
		this.code = code;
		this.rate = rate;
		this.currency = currency;
		this.description = description;
		this.partnerId = partnerId;
		this.airlineId = airlineId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

}
