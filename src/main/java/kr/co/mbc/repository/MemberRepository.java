package kr.co.mbc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	
	@Query(nativeQuery = true, 
		       value = "SELECT count(id) FROM tbl_member WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'username' AND username LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'name' AND name LIKE %:#{#criteria.keyword}%))")
	Long getTotalCount(Criteria criteria);
	
	@Query(nativeQuery = true, 
		       value = "SELECT * FROM tbl_member WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'username' AND username LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'name' AND name LIKE %:#{#criteria.keyword}%)) " +
		               "ORDER BY id LIMIT :#{#criteria.perPageContent} OFFSET :#{#criteria.getOffset()}")
	List<MemberEntity> findMembers(Criteria criteria);

	MemberEntity findByUsername(String username);

	void deleteByUsername(String username);

}
