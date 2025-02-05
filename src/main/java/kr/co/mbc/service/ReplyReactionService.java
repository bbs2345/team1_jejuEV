package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.mbc.entity.ReplyEntity;
import kr.co.mbc.entity.ReplyReactionEntity;
import kr.co.mbc.repository.ReplyReactionRepository;
import kr.co.mbc.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyReactionService {
	
	private final ReplyReactionRepository replyReactionRepository;
	private final ReplyRepository replyRepository;
	
	
    // 반응 저장 메서드
    public boolean processReaction(Long rId, String reactionType, String username) {
        ReplyEntity reply = replyRepository.findById(rId).orElse(null);

        if (reply != null) {
            // ReactionEntity 생성
            ReplyReactionEntity replyReactionEntity = ReplyReactionEntity.builder()
                    .username(username)
                    .reactionType(reactionType)
                    .reply(reply)
                    .build();

            // ReactionEntity 저장
            replyReactionRepository.save(replyReactionEntity);
            return true;
        }
        
        return false;
    }


	public ReplyReactionEntity findByReplyIdAndUsername(Long rId, String username) {
		// TODO Auto-generated method stub
		return replyReactionRepository.findByReplyIdAndUsername( rId,  username);
	}

	@Transactional
	public Object deleteByReplyIdAndUsername(Long rId, String username) {
		// TODO Auto-generated method stub
		return replyReactionRepository.deleteByReplyIdAndUsername( rId,  username);
	}


	public void save(ReplyReactionEntity replyReactionEntity) {
		// TODO Auto-generated method stub
		replyReactionRepository.save(replyReactionEntity);
	}


	public List<ReplyReactionEntity> findByReplyAndReactionType(Optional<ReplyEntity> replyEntity, String like) {
		// TODO Auto-generated method stub
		return replyReactionRepository.findByReplyAndReactionType(replyEntity,like);
	}


	
}