package kr.co.mbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mbc.entity.CateEntity;
import kr.co.mbc.service.CateService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cate")
public class CateController {

	private final CateService cateService;
	
	// 카테고리 삭제기능
	@GetMapping("/delete/{cname}")
	public String deleteCate(@PathVariable("cname") String cname) {
		
		CateEntity cateEntity = cateService.findByCname(cname);
		
		cateService.delete(cateEntity);
		
		return "redirect:/admin/home";
	}
	
	// 카테고리 추가기능
	@PostMapping("/insert")
	public String cateInsert(CateEntity cateEntity) {
		
		cateService.save(cateEntity);
		
		return "redirect:/admin/home";
	}
	
	// 카테고리 추가화면
	@GetMapping("/cateInsert")
	public String cateInsert() {
		return "admin/cateInsert";
	}
	
}
