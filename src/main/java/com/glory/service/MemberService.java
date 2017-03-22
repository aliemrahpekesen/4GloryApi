package com.glory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Member;
import com.glory.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public Member create(Member member) {
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

}
