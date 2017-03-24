package com.glory.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Member {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String surname;
	private Float activePoints;
	private Float blockedPoints;
	private Float burntPoints;
	private String email;
	private String number;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	private List<Card> cards;

	public Member() {
		
	}

	

	public Member(Long id, String name, String surname, Float activePoints, Float blockedPoints, Float burntPoints,
			String email, String number, List<Card> cards) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.activePoints = activePoints;
		this.blockedPoints = blockedPoints;
		this.burntPoints = burntPoints;
		this.email = email;
		this.number = number;
		this.cards = cards;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Float getActivePoints() {
		return activePoints;
	}

	public void setActivePoints(Float activePoints) {
		this.activePoints = activePoints;
	}

	public Float getBlockedPoints() {
		return blockedPoints;
	}

	public void setBlockedPoints(Float blockedPoints) {
		this.blockedPoints = blockedPoints;
	}

	public Float getBurntPoints() {
		return burntPoints;
	}

	public void setBurntPoints(Float burntPoints) {
		this.burntPoints = burntPoints;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
