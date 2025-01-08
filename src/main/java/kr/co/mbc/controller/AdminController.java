package kr.co.mbc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mbc.dto.MemberResponse;
import kr.co.mbc.entity.MemberEntity;
import kr.co.mbc.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;
	
	@GetMapping("/memberList")
	public String memberList(Model model) {
		
		List<MemberEntity> memberEntityList = memberService.findAll();
		
		List<MemberResponse> memberList = new ArrayList<MemberResponse>();
		
		for (MemberEntity memberEntity : memberEntityList) {
			MemberResponse memberResponse = MemberEntity.toMemberResponse(memberEntity);
			memberList.add(memberResponse);
		}
		
		model.addAttribute("memberList", memberList);
		
		return "admin/memberList";
	}
	
}
