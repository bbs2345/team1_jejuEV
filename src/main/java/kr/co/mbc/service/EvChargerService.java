package kr.co.mbc.service;

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
	
}
