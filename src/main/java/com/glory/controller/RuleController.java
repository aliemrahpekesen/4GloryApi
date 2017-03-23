package com.glory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.glory.model.Rule;
import com.glory.service.RuleService;

@RestController
@RequestMapping(path = "/rule", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RuleController {

	@Autowired
	RuleService ruleService;

	@RequestMapping(method = RequestMethod.POST)
	public Rule post(@RequestBody Rule ruleData) {
		return ruleService.create(ruleData);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Rule> getAll() {
		return ruleService.getAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteById(Long id) {
		ruleService.delete(id);
		return "NoContent";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Rule put(@RequestBody Rule ruleData) {
		return ruleService.update(ruleData);
	}

}
