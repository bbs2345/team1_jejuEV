package kr.co.mbc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.mbc.entity.EvChargerEntity;
import kr.co.mbc.repository.EvChargerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvChargerService {

	
	private final EvChargerRepository evChargerRepository;

	public void save(EvChargerEntity chargerEntity) {
		evChargerRepository.save(chargerEntity);
	}


    // 충전기 타입 비율 계산 메서드
    public Map<String, Integer> getChargingTypeRatio() {
        // 전체 충전기 리스트를 가져옵니다.
        List<EvChargerEntity> chargers = evChargerRepository.findAll();

        int totalCount = chargers.size();  // 전체 충전기 수
        int slowCount = 0;  // 완속 충전기 개수
        int fastCount = 0;  // 급속 충전기 개수

        // 충전기 타입을 확인하여 카운팅
        for (EvChargerEntity charger : chargers) {
            String chgerType = charger.getChgerType();  // 충전기 타입 가져오기

            // 완속 (02, 08)인 경우
            if ("02".equals(chgerType) || "08".equals(chgerType)) {
                slowCount++;
            } else {
                fastCount++;
            }
        }

        // 결과를 Map에 담아서 반환
        Map<String, Integer> ratioMap = new HashMap<>();
        ratioMap.put("slowCount", slowCount);
        ratioMap.put("fastCount", fastCount);
        ratioMap.put("totalCount", totalCount);

        return ratioMap;
    }
	
}
