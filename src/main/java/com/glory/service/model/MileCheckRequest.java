package com.glory.service.model;

import com.glory.model.Card;
import com.glory.model.enums.Currency;

public class MileCheckRequest {
	
	private Card cardInfo;
	private String partnerTransactionCode;
	private String companyCode;
	private float amount;
	private Currency currency;
	public Card getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(Card cardInfo) {
		this.cardInfo = cardInfo;
	}
	public String getPartnerTransactionCode() {
		return partnerTransactionCode;
	}
	public void setPartnerTransactionCode(String partnerTransactionCode) {
		this.partnerTransactionCode = partnerTransactionCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	

}
