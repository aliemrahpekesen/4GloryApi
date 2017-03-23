package com.glory.repository;

import org.springframework.data.repository.CrudRepository;
import com.glory.model.Card;


public interface CardRepository extends CrudRepository<Card, Long> {
	public Card findById(Long id);
	public Card findByNumber(String number);
}
