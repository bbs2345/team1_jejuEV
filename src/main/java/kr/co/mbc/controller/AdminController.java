package kr.co.mbc.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.dto.Pagination;
import kr.co.mbc.dto.UserResponse;
import kr.co.mbc.entity.CateEntity;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.service.CateService;
import kr.co.mbc.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;
	private final CateService cateService;
	
	// 관리자화면
	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		List<CateEntity> cateList = cateService.findAll();

		ServletContext context = request.getServletContext();
		context.setAttribute("cateList", cateList);
		
		return "admin/home";
	}
	
	// 회원목록
	@GetMapping("/userList")
	public String userList(@ModelAttribute("criteria") Criteria criteria, Model model) {
		
		List<UserEntity> userEntities = userService.findMembers(criteria);
		
	    List<UserResponse> userList = new ArrayList<>();
	    for (UserEntity userEntity : userEntities) {
	        UserResponse userResponse = UserEntity.toUserResponse(userEntity);
	        userList.add(userResponse);
	    }
		
		Long totalCount = userService.getTotalCount(criteria);
		
		Pagination pagination = new Pagination(criteria, totalCount);
		
		model.addAttribute("userList", userList);
		model.addAttribute("pagination", pagination);
		
		return "admin/userList";
	}
	
}
