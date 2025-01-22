package kr.co.mbc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.BoardReactionEntity;
import kr.co.mbc.entity.ReplyEntity;

public interface BoardReactionRepository extends JpaRepository<BoardReactionEntity, Long>{
	Optional<BoardReactionEntity> findByUsernameAndBoardId(String username, Long boardId);

	void save(BoardEntity boardEntity);

	BoardReactionEntity findByBoardIdAndUsername(Long boardId, String username);

	Object deleteByBoardIdAndUsername(Long boardId, String username);

	List<BoardReactionEntity> findByBoardAndReactionType(BoardEntity dto, String like);
}
