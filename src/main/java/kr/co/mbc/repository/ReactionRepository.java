package kr.co.mbc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReactionEntity;
import kr.co.mbc.entity.ReplyEntity;

public interface ReactionRepository extends JpaRepository<ReactionEntity, Long>{
	Optional<ReactionEntity> findByUsernameAndBoardId(String username, Long boardId);

	void save(BoardEntity boardEntity);

	void save(ReplyEntity replyEntity);

	ReactionEntity findByBoardIdAndUsername(Long boardId, String username);

	Object deleteByBoardIdAndUsername(Long boardId, String username);

	List<ReactionEntity> findByBoardAndReactionType(BoardEntity dto, String like);
}
