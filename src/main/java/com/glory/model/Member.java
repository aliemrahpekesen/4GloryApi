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
	private long activePoints;
	private long blockedPoints;
	private long burntPoints;
	private String email;
	private String number;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	private List<Card> cards;

	public Member() {
	}

	public Member(Long id, String name, String surname, List<Card> cards) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.cards = cards;
		this.burntPoints =0L;
		this.activePoints=20000L;
		this.blockedPoints=0L;
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

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public long getActivePoints() {
		return activePoints;
	}

	public void setActivePoints(long activePoints) {
		this.activePoints = activePoints;
	}

	public long getBlockedPoints() {
		return blockedPoints;
	}

	public void setBlockedpoints(long blockedPoints) {
		this.blockedPoints = blockedPoints;
	}

	public long getBurntPoints() {
		return burntPoints;
	}

	public void setBurntPoints(long burntPoints) {
		this.burntPoints = burntPoints;
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
