package com.glory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.glory.model.Card;
import com.glory.service.CardService;

@RestController
@RequestMapping(path = "/card", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

	@Autowired
	CardService cardService;

	@RequestMapping(method = RequestMethod.POST)
	public Card post(@RequestBody Card cardData) {
		return cardService.create(cardData);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Card> getAll() {
		return cardService.getAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteById(Long id) {
		cardService.delete(id);
		return "NoContent";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Card put(@RequestBody Card cardData) {
		return cardService.update(cardData);
	}

}
