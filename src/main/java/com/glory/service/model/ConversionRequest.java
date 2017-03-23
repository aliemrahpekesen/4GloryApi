package com.glory.service.model;

public class ConversionRequest {
	private String partnerCompanyCode;
	private String ffpProgramCode;
	private float monetaryAmount;
	
	public String getPartnerCompanyCode() {
		return partnerCompanyCode;
	}
	public void setPartnerCompanyCode(String partnerCompanyCode) {
		this.partnerCompanyCode = partnerCompanyCode;
	}
	public String getFfpProgramCode() {
		return ffpProgramCode;
	}
	public void setFfpProgramCode(String ffpProgramCode) {
		this.ffpProgramCode = ffpProgramCode;
	}
	public float getMonetaryAmount() {
		return monetaryAmount;
	}
	public void setMonetaryAmount(float monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}
	
	

}
