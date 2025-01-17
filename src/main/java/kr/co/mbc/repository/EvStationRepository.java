package kr.co.mbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.EvChargingStationEntity;

public interface EvStationRepository extends JpaRepository<EvChargingStationEntity, Long> {
	
	// 검색 기능
	@Query(nativeQuery = true, 
		       value = "SELECT count(id) FROM tbl_evchargerstation WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'stat_nm' AND stat_nm LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'addr' AND addr LIKE %:#{#criteria.keyword}%))")
	Long getTotalCount(Criteria criteria);
	
	@Query(nativeQuery = true, 
		       value = "SELECT * FROM tbl_evchargerstation WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'stat_nm' AND stat_nm LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'addr' AND addr LIKE %:#{#criteria.keyword}%)) " +
		               "ORDER BY id LIMIT :#{#criteria.perPageContent} OFFSET :#{#criteria.getOffset()}")
	
	List<EvChargingStationEntity> findAll(Criteria criteria);

	List<EvChargingStationEntity> findByStatNm(String statNm);

	boolean existsByStatIdAndAddrAndLatAndLng(String statId, String addr, String lat, String lng);
	
}
