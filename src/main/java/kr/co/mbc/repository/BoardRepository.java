package kr.co.mbc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReactionEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

	Optional<BoardEntity> findById(Long id);

	void deleteById(Long id);

	@Query(nativeQuery = true, 
		       value = "SELECT count(id) FROM tbl_board WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'title' AND title LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'writer' AND writer LIKE %:#{#criteria.keyword}%))")
	Long getTotalCount(Criteria criteria);

	@Query(nativeQuery = true, 
		       value = "SELECT * FROM tbl_board WHERE " +
		               "(:#{#criteria.type} IS NULL OR " +
		               "(:#{#criteria.type} = 'title' AND title LIKE %:#{#criteria.keyword}%) OR " +
		               "(:#{#criteria.type} = 'writer' AND writer LIKE %:#{#criteria.keyword}%)) " +
		               "ORDER BY id desc LIMIT :#{#criteria.perPageContent} OFFSET :#{#criteria.getOffset()}")
	List<BoardEntity> findAll(Criteria criteria);



}
