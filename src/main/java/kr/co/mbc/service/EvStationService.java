package kr.co.mbc.service;

import java.util.List;

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

	    // 공백을 기준으로 나누기 (최대 3부분으로 나누기)
	    String[] parts = address.split(" ", 3); // 첫 번째는 시 이름, 두 번째는 읍/면

	    // 첫 번째 부분은 시 이름 (제주시, 서귀포시)
	    String city = parts[0];

	    // 두 번째 부분에서 읍/면 추출
	    if (parts.length > 1) {
	        String secondPart = parts[1];

	        // 읍/면이 있으면 읍/면만 반환
	        if (secondPart.endsWith("읍") || secondPart.endsWith("면")) {
	            return secondPart;
	        }
	    }

	    // 읍/면이 없으면 시 이름만 반환
	    if (city != null && !city.isEmpty()) {
	        return city;
	    }

	    // 추출되지 않은 경우 로그 출력
	    System.out.println("추출되지 않은 주소: " + address);
	    return null; // 유효한 행정구역을 찾을 수 없으면 null 반환
	}
}
