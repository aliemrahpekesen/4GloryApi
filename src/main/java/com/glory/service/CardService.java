package com.glory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Card;
import com.glory.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	CardRepository cardRepository;

	public Card create(Card Card) {
		return cardRepository.save(Card);
	}

	public List<Card> getAll() {
		return (List<Card>) cardRepository.findAll();
	}

	public void delete(Long id) {
		if (id == 0) {
			cardRepository.deleteAll();
		} else {
			cardRepository.delete(id);
		}
	}

	public Card update(Card card) {		
		return cardRepository.save(card);
	}
	
	public Card findByNumber(String number){
		return cardRepository.findByNumber(number);
	}

}
