package kr.co.mbc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.EvchagerEntity;
import kr.co.mbc.service.EvchargerService;
import kr.co.mbc.utils.Pagination;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/evcharger")
@RequiredArgsConstructor
public class EvChargerController {
	
	private final EvchargerService evChargerService;
	
	private final String apiUrl = "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo";
	private final String apikey = "zdcb/dCnKTh9GswIuVYaVktIh68IRBPbWnnyqE6jqG5npU0ztdVPnHwocG98iL0HPqqufx2nKNqdbowVwrBu0Q==";
	
	// 충전소 목록
	@GetMapping("/list")
	public String list(Criteria criteria, Model model) {
		
		List<EvchagerEntity> evList = evChargerService.findAll(criteria);
		
		Long totalCount = evChargerService.getTotalCount(criteria);
		
		Pagination pagination = new Pagination(criteria, totalCount);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("criteria", criteria);
		model.addAttribute("evList", evList);
		
		return "evcharger/list";
	}
	
	// API받고 DB에 저장
	@GetMapping("/fetch-evchargers")
	@ResponseBody
	public String fetchAndSaveData() {
	    try {
	        // API에서 XML 데이터 가져오기
	        String xmlData = evChargerService.fetchData(apiUrl, apikey);

	        // XML 데이터를 데이터베이스에 저장
	        evChargerService.saveDataFromXml(xmlData);

	        return "데이터 저장 성공~!"; // 성공 메시지 반환
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 로그 출력
	        return "에러 이유 : " + e.getMessage(); // 에러 메시지 반환
	    }
	}

}
