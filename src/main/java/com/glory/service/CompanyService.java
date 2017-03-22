package com.glory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Company;
import com.glory.repository.CompanyRepository;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Company retrieveById(Long id) {
		return companyRepository.findOne(id);
	}

	public Company retrieveByCode(String code) {
		return companyRepository.findByCode(code);
	}

	public void delete(Long id) {
		try {
			if (id == 0) {
				companyRepository.deleteAll();
			} else {
				companyRepository.delete(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
