package kr.co.mbc.service;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.EvStationEntity;
import kr.co.mbc.repository.EvStationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvStationService {

	private final EvStationRepository evStationRepository;

	public EvStationEntity findByStatId(String statId) {
		return evStationRepository.findByStatId(statId);
	}

	public void save(EvStationEntity stationEntity) {
		evStationRepository.save(stationEntity);
	}

	public List<EvStationEntity> findAll(Criteria criteria) {
		return evStationRepository.findAll(criteria);
	}

	public Long getTotalCount(Criteria criteria) {
		
		return evStationRepository.getTotalCount(criteria);
	}
	
	
	public List<EvStationEntity> findAll() {
		return evStationRepository.findAll();
	}

	public String extractDistrictName(String address, List<String> districts) {
	       // 주소 앞부분 공백 제거
	       address = address.trim();

	       // "제주" 또는 "제주특별자치도" 부분 제거
	       if (address.startsWith("제주특별자치도")) {
	           address = address.substring("제주특별자치도".length()).trim();
	       } else if (address.startsWith("제주")) {
	           address = address.substring("제주".length()).trim();
	       }

	       // StringTokenizer를 사용하여 공백을 기준으로 토큰화
	        StringTokenizer tokenizer = new StringTokenizer(address, " ");

	        // 첫 번째 토큰 (제주시 or 서귀포시)
	        if (!tokenizer.hasMoreTokens()) {
	            System.out.println("추출되지 않은 주소: " + address);
	            return null;
	        }
	        String city = tokenizer.nextToken(); // 시 이름

	        // 두 번째 토큰 (읍/면 여부 확인)
	        if (tokenizer.hasMoreTokens()) {
	            String secondPart = tokenizer.nextToken();

	            // 읍/면이 포함된 경우 반환
	            if (secondPart.endsWith("읍") || secondPart.endsWith("면")) {
	                return secondPart;
	            }
	        }

	        // 읍/면이 없으면 시 이름 반환
	        return city;
	    }

}
