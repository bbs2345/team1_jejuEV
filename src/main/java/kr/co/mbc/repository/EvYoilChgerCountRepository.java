package kr.co.mbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.EvChgeYoilEntity;

public interface EvYoilChgerCountRepository extends JpaRepository<EvChgeYoilEntity, Long>{
	
	List<EvChgeYoilEntity> findAll();
}
