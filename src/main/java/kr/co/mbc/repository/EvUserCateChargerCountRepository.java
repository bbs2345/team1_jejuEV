package kr.co.mbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.EvChgerCountEntity;

public interface EvUserCateChargerCountRepository extends JpaRepository<EvChgerCountEntity, Long>{
	
	List<EvChgerCountEntity> findAll();

}
