package kr.co.mbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.EvChargerEntity;

public interface EvChargerRepository extends JpaRepository<EvChargerEntity, Long>{
	
	// 충전기 데이터를 모두 가져오는 기본 메서드를 제공합니다.
    List<EvChargerEntity> findAll();

}
