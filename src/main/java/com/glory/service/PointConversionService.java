package com.glory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Company;
import com.glory.model.Rule;
import com.glory.service.model.ConversionRequest;
import com.glory.service.model.ConversionResponse;

@Service
public class PointConversionService {

	@Autowired
	CompanyService companyService;

	@Autowired
	RuleService ruleService;

	public ConversionResponse convert(ConversionRequest conversionRequest) {
		// TODO Auto-generated method stub
		ConversionResponse conversionResponse = null;
		// first find the rule
		Company partnerCompany = companyService.retrieveByCode(conversionRequest.getPartnerCompanyCode());
		Company ffpCompany = companyService.retrieveByCode(conversionRequest.getFfpProgramCode());
		Rule rule = findRule(partnerCompany.getCode(), ffpCompany.getCode());

		// then convert and return
		conversionResponse = convert(rule, conversionRequest.getMonetaryAmount());

		return  conversionResponse;
	}

	private Rule findRule(String partnerCompanyCode, String ffpCompanyCode) {
		Rule result = null;
		try {
			Company partnerCompany = companyService.retrieveByCode(partnerCompanyCode);
			Company ffpCompany = companyService.retrieveByCode(ffpCompanyCode);
			List<Rule> rules = ruleService.getRuleByPartnerIdAndAirlineId(partnerCompany.getId(), ffpCompany.getId());

			result = rules.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private ConversionResponse convert(Rule rule, float monetaryAmount) {
		ConversionResponse result = new ConversionResponse();
		result.setMonetaryAmount(monetaryAmount);
		if (monetaryAmount < 0) {
			result.setMessage("monetary amount cannot be negative");
		} else {
			result.setMessage("OK");
			result.setMilesAmount((float) (monetaryAmount / rule.getRate()));
		}

		return result;
	}
}
