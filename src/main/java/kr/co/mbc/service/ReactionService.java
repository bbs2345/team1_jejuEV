package kr.co.mbc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReactionEntity;
import kr.co.mbc.entity.ReplyEntity;
import kr.co.mbc.repository.BoardRepository;
import kr.co.mbc.repository.ReactionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReactionService {
	
	private final ReactionRepository reactionRepository;
	private final BoardRepository boardRepository;
	
	
    // 반응 저장 메서드
    public boolean processReaction(Long boardId, String reactionType, String username) {
        BoardEntity board = boardRepository.findById(boardId).orElse(null);

        if (board != null) {
            // ReactionEntity 생성
            ReactionEntity reactionEntity = ReactionEntity.builder()
                    .username(username)
                    .reactionType(reactionType)
                    .board(board)
                    .build();

            // ReactionEntity 저장
            reactionRepository.save(reactionEntity);
            return true;
        }
        return false;
    }


	public ReactionEntity findByBoardIdAndUsername(Long boardId, String username) {
		// TODO Auto-generated method stub
		return reactionRepository.findByBoardIdAndUsername( boardId,  username);
	}

	@Transactional
	public Object deleteByBoardIdAndUsername(Long boardId, String username) {
		// TODO Auto-generated method stub
		return reactionRepository.deleteByBoardIdAndUsername( boardId,  username);
	}


	public void save(ReactionEntity reactionEntity) {
		// TODO Auto-generated method stub
		reactionRepository.save(reactionEntity);
	}
	
	
}