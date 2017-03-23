package com.glory.repository;

import org.springframework.data.repository.CrudRepository;

import com.glory.model.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
  public Member findByCardsId(Long id);
}
