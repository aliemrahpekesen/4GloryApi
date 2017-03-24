package com.glory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Card {

	@Id
	@GeneratedValue
	private Long id;
	private String number;
	private String expireMonth;
	private String expireYear;
	private Integer cvv;
	private String ffpCode;
	private String nameOnCard;
	private String magneticStripeData;

	public Card() {
	}

	public Card(Long id, String number, String expireMonth, String expireYear, Integer cvv, String ffpCode,
			String nameOnCard, String magneticStripeData) {
		super();
		this.id = id;
		this.number = number;
		this.expireMonth = expireMonth;
		this.expireYear = expireYear;
		this.cvv = cvv;
		this.ffpCode = ffpCode;
		this.nameOnCard = nameOnCard;
		this.magneticStripeData = magneticStripeData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public String getExpireMonth() {
		return expireMonth;
	}

	public void setExpireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
	}

	public String getExpireYear() {
		return expireYear;
	}

	public void setExpireYear(String expireYear) {
		this.expireYear = expireYear;
	}

	public String getFfpCode() {
		return ffpCode;
	}

	public void setFfpCode(String ffpCode) {
		this.ffpCode = ffpCode;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getMagneticStripeData() {
		return magneticStripeData;
	}

	public void setMagneticStripeData(String magneticStripeData) {
		this.magneticStripeData = magneticStripeData;
	}

}
