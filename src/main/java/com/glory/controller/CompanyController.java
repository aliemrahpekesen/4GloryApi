package com.glory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glory.model.Company;
import com.glory.service.CompanyService;

@RestController
@RequestMapping(path = "/company", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Company retrieveById(@PathVariable("id") Long id) {
		return companyService.retrieveById(id);
	}

	@RequestMapping(path = "/{code}", method = RequestMethod.GET)
	public @ResponseBody Company retrieveById(@PathVariable("code") String code) {
		return companyService.retrieveByCode(code);
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Company> retrieveAll() {
		return companyService.retrieveAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Company create(@RequestBody(required = true) Company company) {
		return companyService.save(company);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteById(Long id) {
		companyService.delete(id);
		return "NoContent";
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Company put(@PathVariable("id") String id, @RequestBody Company companyData) {
		Long companyId = Long.parseLong(id);
		return companyService.update(companyId, companyData);
	}

}
