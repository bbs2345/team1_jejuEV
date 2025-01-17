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
}
