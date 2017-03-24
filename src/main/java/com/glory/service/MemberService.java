package com.glory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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

	public Member update(Long id, Member member) {
		member.setId(id);
		List<Card> cardOfMember = memberRepository.findById(id).getCards();
		member.setCards(cardOfMember);
		return memberRepository.save(member);
	}

	public Member findByCardId(Long id) {
		return memberRepository.findByCardsId(id);
	}

	public Member findByMemberId(Long id) {
		return memberRepository.findById(id);
	}

	private void setupMockMemberAccount(Member member) {
		member.setBurntPoints(0F);
		member.setActivePoints(20000F);
		member.setBlockedPoints(0F);
		Card card = new Card();
		Random rand = new Random();
		int value = rand.nextInt(899) + 100;
		card.setCvv(value);
		String randExpireMonth = String.valueOf(rand.nextInt(12));
		card.setExpireMonth(randExpireMonth.length() < 2 ? "0" + randExpireMonth : randExpireMonth);
		card.setExpireYear(String.valueOf(rand.nextInt(10) + 17));
		card.setFfpCode("TK");
		card.setNumber(
				"666" + String.valueOf(ThreadLocalRandom.current().nextLong(10000000000000000L, 90000000000000000L)));
		card.setNameOnCard(member.getName() + " " + member.getSurname());
		card.setMagneticStripeData(UUID.randomUUID().toString());
		List<Card> cards = new ArrayList<Card>();
		cards.add(card);
		member.setCards(cards);
	}

}
