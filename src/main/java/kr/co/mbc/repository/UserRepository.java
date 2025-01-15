package kr.co.mbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	@Query(nativeQuery = true, 
		       value = "SELECT count(id) FROM tbl_user WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'username' AND username LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'name' AND name LIKE %:#{#criteria.keyword}%))")
	Long getTotalCount(Criteria criteria);
	
	@Query(nativeQuery = true, 
		       value = "SELECT * FROM tbl_user WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'username' AND username LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'name' AND name LIKE %:#{#criteria.keyword}%)) " +
		               "ORDER BY id LIMIT :#{#criteria.perPageContent} OFFSET :#{#criteria.getOffset()}")
	List<UserEntity> findMembers(Criteria criteria);

	UserEntity findByUsername(String username);

	void deleteByUsername(String username);

	UserEntity findByUsernameAndPassword(String username, String password);

}
