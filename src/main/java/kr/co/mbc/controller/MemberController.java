package kr.co.mbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mbc.dto.MemberForm;
import kr.co.mbc.dto.MemberResponse;
import kr.co.mbc.entity.MemberEntity;
import kr.co.mbc.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/read/{username}")
	public String read(@PathVariable("username") String username, Model model) {
		
		MemberEntity memberEntity = memberService.findByUsername(username);
		
		MemberResponse memberResponse = MemberEntity.toMemberResponse(memberEntity);
		
		model.addAttribute("memberResponse", memberResponse);
		
		return "member/read";
	}
	
	// 회원가입 처리
	@PostMapping("/insert")
	public String insert(MemberForm memberForm) {
		
		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberForm);
		
		memberService.save(memberEntity);
		
		return "redirect:/";
	}
	
	// 회원가입 페이지
	@GetMapping("/insertForm")
	public String insertForm() {
		return "member/insertForm";
	}
	
}
