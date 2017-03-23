package com.glory.service.model;

public class ConversionResponse {
	
	private float monetaryAmount;
	private float milesAmount;
    private String message;
    
	public float getMonetaryAmount() {
		return monetaryAmount;
	}
	public void setMonetaryAmount(float monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}
	public float getMilesAmount() {
		return milesAmount;
	}
	public void setMilesAmount(float milesAmount) {
		this.milesAmount = milesAmount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
