package com.glory.repository;

import org.springframework.data.repository.CrudRepository;

import com.glory.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	public Company findByCode(String code);
}
