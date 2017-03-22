package com.glory.service.requests;

import com.glory.model.Card;

public class CheckMilesRequest {

	private String partnerTransactionCode;
	private Card cardInfo;
	private String partnerCode;
	private double monetaryAmount;
	private String Currency;

	public String getPartnerTransactionCode() {
		return partnerTransactionCode;
	}

	public void setPartnerTransactionCode(String partnerTransactionCode) {
		this.partnerTransactionCode = partnerTransactionCode;
	}

	public Card getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(Card cardInfo) {
		this.cardInfo = cardInfo;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public double getMonetaryAmount() {
		return monetaryAmount;
	}

	public void setMonetaryAmount(double monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

}
