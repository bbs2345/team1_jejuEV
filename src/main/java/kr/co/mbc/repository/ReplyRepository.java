package kr.co.mbc.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
	Page<ReplyEntity> findAllByBoardId(Long bId, Pageable pageable);
	Page<ReplyEntity> findByBoardId(Long bId, Pageable pageable);
	List<ReplyEntity> findByBoardId(BoardEntity boardEntity);
	Page<ReplyEntity> findByBoardIdOrderByWriteDateDesc(Long bId, Pageable pageable);




}
