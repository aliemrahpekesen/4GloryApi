package com.glory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.glory.model.Member;
import com.glory.service.MemberService;

@RestController
@RequestMapping(path = "/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping(method = RequestMethod.POST)
	public Member post(@RequestBody Member memberData) {
		return memberService.create(memberData);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Member> getAll() {
		return memberService.getAll();
	}

	@CrossOrigin(origins="*")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteById(Long id) {
		memberService.delete(id);
		return "NoContent";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Member put(@RequestBody Member memberData){
		return memberService.update(memberData);
	}

}