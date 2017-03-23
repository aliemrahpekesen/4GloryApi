package com.glory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Card;
import com.glory.model.Member;
import com.glory.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public Member create(Member member) {		
		setupMockMemberAccount(member);		
		return memberRepository.save(member);
	}

	public List<Member> getAll() {
		return (List<Member>) memberRepository.findAll();
	}

	public void delete(Long id) {
		if (id == 0) {
			memberRepository.deleteAll();
		} else {
			memberRepository.delete(id);
		}
	}

	public Member update(Long id,Member member) {
		member.setId(id);
		return memberRepository.save(member);
	}
	
	public Member findByCardId(Long id){
		return memberRepository.findByCardsId(id);
	}
	
	public Member findByMemberId(Long id){
		return memberRepository.findById(id);
	}

	private void setupMockMemberAccount(Member member) {
		member.setBurntPoints(0L);
		member.setActivePoints(20000L);
		member.setBlockedPoints(0L);
		Card card = new Card();
		Random rand = new Random();
		int value = rand.nextInt(899) + 100;
		card.setCvv(value);
		card.setExpireMonth("05");
		card.setExpireYear(String.valueOf(rand.nextInt(10) + 17));
		card.setFfpCode("TK");
		card.setNumber(
				"666" + String.valueOf(ThreadLocalRandom.current().nextLong(10000000000000000L, 90000000000000000L)));
		List<Card> cards = new ArrayList<Card>();
		cards.add(card);
		member.setCards(cards);
	}

}
