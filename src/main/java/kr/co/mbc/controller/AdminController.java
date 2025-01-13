package kr.co.mbc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.dto.MemberResponse;
import kr.co.mbc.entity.MemberEntity;
import kr.co.mbc.service.MemberService;
import kr.co.mbc.utils.Pagination;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;
	
	// 회원목록
	@GetMapping("/memberList")
	public String memberList(Criteria criteria, Model model) {
		
		List<MemberEntity> memberEntityList = memberService.findMembers(criteria);
		
		// MemberEntity -> MemberResponse로 변환
	    List<MemberResponse> memberList = new ArrayList<>();
	    for (MemberEntity memberEntity : memberEntityList) {
	        MemberResponse memberResponse = MemberEntity.toMemberResponse(memberEntity);
	        memberList.add(memberResponse);
	    }
		
		Long totalCount = memberService.getTotalCount(criteria);
		
		Pagination pagination = new Pagination(criteria, totalCount);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("criteria", criteria);
		
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::");
		System.out.println();
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::");
		
		return "admin/memberList";
	}
	
}
