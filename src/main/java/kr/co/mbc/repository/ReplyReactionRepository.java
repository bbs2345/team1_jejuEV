package kr.co.mbc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.ReplyEntity;
import kr.co.mbc.entity.ReplyReactionEntity;

public interface ReplyReactionRepository extends JpaRepository<ReplyReactionEntity, Long>{
	Optional<ReplyReactionEntity> findByUsernameAndReplyId(String username, Long rId);

	void save(ReplyEntity replyEntity);

	ReplyReactionEntity findByReplyIdAndUsername(Long rId, String username);

	Object deleteByReplyIdAndUsername(Long rId, String username);

	List<ReplyReactionEntity> findByReplyAndReactionType(Optional<ReplyEntity> replyEntity, String like);
}
