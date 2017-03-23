package com.glory.repository;

import org.springframework.data.repository.CrudRepository;

import com.glory.model.Rule;
import java.util.List;

public interface RuleRepository extends CrudRepository<Rule, Long>{
     List<Rule> findByPartnerIdAndAirlineId(Long partnerId, Long AirlineId);
}
