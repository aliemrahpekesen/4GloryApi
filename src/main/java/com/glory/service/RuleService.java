package com.glory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Rule;
import com.glory.repository.RuleRepository;

@Service
public class RuleService {
	
	@Autowired
	RuleRepository ruleRepository;
	
	public Rule create(Rule Rule) {
		return ruleRepository.save(Rule);
	}

	public List<Rule> getAll() {
		return (List<Rule>) ruleRepository.findAll();
	}

	public void delete(Long id) {
		if (id == 0) {
			ruleRepository.deleteAll();
		} else {
			ruleRepository.delete(id);
		}
	}

	public Rule update(Rule rule) {		
		return ruleRepository.save(rule);
	}
	
	public List<Rule> getRuleByPartnerIdAndAirlineId(Long partnerId, Long airlineId){
		return ruleRepository.findByPartnerIdAndAirlineId(partnerId, airlineId);
	}
	

}
