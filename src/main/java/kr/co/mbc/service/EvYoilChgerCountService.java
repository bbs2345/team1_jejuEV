package kr.co.mbc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.mbc.entity.EvChgeYoilEntity;
import kr.co.mbc.repository.EvYoilChgerCountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvYoilChgerCountService {
	
	private final EvYoilChgerCountRepository evYoilChgerCountRepository;

	public Map<String, Integer> getYoilChgerCountByBaseDayOfTheWeek() {
        List<EvChgeYoilEntity> chargingData = evYoilChgerCountRepository.findAll();
        Map<String, Integer> chargingCountByDay = new HashMap<>();

        // 요일별 충전횟수 합산
        for (EvChgeYoilEntity entity : chargingData) {
            String dayOfWeek = entity.getBaseDayOfTheWeek();
            int count = entity.getChargingCount();

            chargingCountByDay.put(dayOfWeek, chargingCountByDay.getOrDefault(dayOfWeek, 0) + count);
        }

        return chargingCountByDay;
    }
	
	
}
