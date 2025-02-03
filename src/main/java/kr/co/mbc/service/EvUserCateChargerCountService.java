package kr.co.mbc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.mbc.entity.EvChgerCountEntity;
import kr.co.mbc.repository.EvUserCateChargerCountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvUserCateChargerCountService {
	
	private final EvUserCateChargerCountRepository evUserCateChargerCountRepository;

    public Map<String, Integer> getUsercateCount() {
        List<EvChgerCountEntity> users = evUserCateChargerCountRepository.findAll();
        int domin = 0;
        int tourist = 0;

        for (EvChgerCountEntity user : users) {
        	
            if (user.getUserCategory() == null) {
                continue;  // null인 경우, 카운트하지 않고 건너뜀
            }
            
            if (user.getUserCategory() != null) {
                if (user.getUserCategory().equals("도민")) {
                	domin++;
                } else if (user.getUserCategory().equals("관광객")) {
                	tourist++;
                }
            }
        }

        
        Map<String, Integer> result = new HashMap<>();
        result.put("도민", domin);
        result.put("관광객", tourist);

        return result;
    }

}
