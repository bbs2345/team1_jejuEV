package kr.co.mbc.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.mbc.dto.ApiResponseDto;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.EvChargerEntity;
import kr.co.mbc.entity.EvStationEntity;
import kr.co.mbc.service.EvChargerService;
import kr.co.mbc.service.EvStationService;
import kr.co.mbc.utils.Pagination;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ev")
@RequiredArgsConstructor
public class EvController {
	
	private final EvStationService evStationService;
	private final EvChargerService evChargerService;
	
	@GetMapping("/getChargerInfo/{statId}")
	@ResponseBody
	public List<ApiResponseDto> getChargerInfo(@PathVariable("statId") String statId) throws IOException {
		RestTemplate restTemplate = new RestTemplate(); // 직접 인스턴스 생성
		String url = "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo";
		String serviceKey = "zdcb/dCnKTh9GswIuVYaVktIh68IRBPbWnnyqE6jqG5npU0ztdVPnHwocG98iL0HPqqufx2nKNqdbowVwrBu0Q==";
		
		StringBuilder builder = new StringBuilder(url);
        builder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); 
        builder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        builder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8"));
        builder.append("&" + URLEncoder.encode("zcode","UTF-8") + "=" + URLEncoder.encode("50", "UTF-8"));
        builder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
        builder.append("&" + URLEncoder.encode("statId","UTF-8") + "=" + URLEncoder.encode(statId, "UTF-8"));
        String fullUrl = builder.toString();
		
		String response = restTemplate.getForObject(fullUrl, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response);

        // items.item 배열 추출
        JsonNode items = root.path("items").path("item");
		
        List<ApiResponseDto> list = new ArrayList<ApiResponseDto>();
        for (JsonNode item : items) {
        	ApiResponseDto dto = ApiResponseDto.builder()
        			.chgerId(item.path("chgerId").asText()).chgerType(item.path("chgerType").asText()).stat(item.path("stat").asText()).statUpdDt(item.path("statUpdDt").asText())
        			.lastTsdt(item.path("lastTsdt").asText()).lastTedt(item.path("lastTedt").asText()).nowTsdt(item.path("nowTsdt").asText())
        			.powerType(item.path("powerType").asText()).output(item.path("output").asText()).method(item.path("method").asText()).build();
        	
        	list.add(dto);
		}
		
		return list;
	}
	
	// 충전소(상세보기) 클릭! -> 이쪽으로 오도록 하고 -> 클릭한 충전소의 statId가지고 api요청해서 http://apis.data.go.kr/B552584/EvCharger/getChargerInfo?serviceKey=zdcb/dCnKTh9GswIuVYaVktIh68IRBPbWnnyqE6jqG5npU0ztdVPnHwocG98iL0HPqqufx2nKNqdbowVwrBu0Q==&
	// 충전소 상세보기 페이지
	@GetMapping("/read/{statId}")
	public String read(@PathVariable("statId") String statId, Model model){
		
		EvStationEntity stationEntity = evStationService.findByStatId(statId);
		
		model.addAttribute("stationEntity", stationEntity);
		
		return "ev/read";
	}
	
	
	
	// 충전소 목록
	@GetMapping("/list")
	public String list(Criteria criteria, Model model) {
		// 목록페이지로 이동하면 밑에줄 다 실행 --> criteria 
		List<EvStationEntity> stList = evStationService.findAll(criteria);
		
		Long totalCount = evStationService.getTotalCount(criteria);
		
		Pagination pagination = new Pagination(criteria, totalCount);
		
		model.addAttribute("stList", stList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("criteria", criteria);
		
		return "/ev/list";
	}
	
	
	@GetMapping("/getData")
	public String getData() throws IOException {
		RestTemplate restTemplate = new RestTemplate(); // 직접 인스턴스 생성
		String url = "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo";
		String serviceKey = "zdcb/dCnKTh9GswIuVYaVktIh68IRBPbWnnyqE6jqG5npU0ztdVPnHwocG98iL0HPqqufx2nKNqdbowVwrBu0Q==";
		
		StringBuilder builder = new StringBuilder(url); /*URL*/
        builder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
        builder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        builder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("9999", "UTF-8")); /*한 페이지 결과 수 (최소 10, 최대 9999)*/
        builder.append("&" + URLEncoder.encode("zcode","UTF-8") + "=" + URLEncoder.encode("50", "UTF-8")); /*시도 코드 (행정구역코드 앞 2자리)*/
        builder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
        // builder.append("&" + URLEncoder.encode("statId","UTF-8") + "=" + URLEncoder.encode("###", "UTF-8"));
        String fullUrl = builder.toString();
		
		
		String response = restTemplate.getForObject(fullUrl, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response);

        // items.item 배열 추출
        JsonNode items = root.path("items").path("item");
        
        for (JsonNode item : items) {
        	
        	EvStationEntity stationEntity = EvStationEntity.builder()
        			.statId(item.path("statId").asText()).statNm(item.path("statNm").asText()).addr(item.path("addr").asText()).addrDetail(item.path("addrDetail").asText())
        			.location(item.path("location").asText()).useTime(item.path("useTime").asText())
        			.lat(item.path("lat").asText()).lng(item.path("lng").asText()).busiId(item.path("busiId").asText()).busiNm(item.path("busiNm").asText())
        			.bnm(item.path("bnm").asText()).busiCall(item.path("busiCall").asText()).zcode(item.path("zcode").asText())
        			.zscode(item.path("zscode").asText()).kind(item.path("kind").asText()).kindDetail(item.path("kindDetail").asText()).parkingFree(item.path("parkingFree").asText())
        			.note(item.path("note").asText()).limitYn(item.path("limitYn").asText()).limitDetail(item.path("limitDetail").asText())
        			.delYn(item.path("delYn").asText()).delDetail(item.path("delDetail").asText()).trafficYn(item.path("trafficYn").asText()).build();
        	
        	// statId로 DB조회
        	String statId = item.path("statId").asText();
        	EvStationEntity dbEntity = evStationService.findByStatId(statId);
        	
        	if (dbEntity == null) {
        		evStationService.save(stationEntity); // db저장
        	}
        	
        	EvChargerEntity chargerEntity = EvChargerEntity.builder()
        			.chgerId(item.path("chgerId").asText()).chgerType(item.path("chgerType").asText()).stat(item.path("stat").asText()).statUpdDt(item.path("statUpdDt").asText())
        			.lastTsdt(item.path("lastTsdt").asText()).lastTedt(item.path("lastTedt").asText()).nowTsdt(item.path("nowTsdt").asText())
        			.powerType(item.path("powerType").asText()).output(item.path("output").asText()).method(item.path("method").asText()).station(stationEntity).build();
        	
        	evChargerService.save(chargerEntity);

        }
		
		return "ev/home";
	}
	
	@GetMapping("/home")
	public String home() {
		return "ev/home";
	}

}
