package kr.co.mbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.EvStationEntity;

public interface EvStationRepository extends JpaRepository<EvStationEntity, String> {
	
	@Query(nativeQuery = true, 
		       value = "SELECT count(stat_id) FROM tbl_evstation WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'stat_nm' AND stat_nm LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'addr' AND addr LIKE %:#{#criteria.keyword}%))")
	Long getTotalCount(Criteria criteria);

	@Query(nativeQuery = true, 
		       value = "SELECT * FROM tbl_evstation WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'stat_nm' AND stat_nm LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'addr' AND addr LIKE %:#{#criteria.keyword}%)) " +
		               "ORDER BY stat_id LIMIT :#{#criteria.perPageContent} OFFSET :#{#criteria.getOffset()}")
	List<EvStationEntity> findAll(Criteria criteria);

	EvStationEntity findByStatId(String statId);


}
