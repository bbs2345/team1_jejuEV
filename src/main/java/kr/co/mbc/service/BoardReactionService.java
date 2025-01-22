package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.BoardReactionEntity;
import kr.co.mbc.entity.ReplyEntity;
import kr.co.mbc.repository.BoardRepository;
import kr.co.mbc.repository.BoardReactionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardReactionService {
	
	private final BoardReactionRepository boardReactionRepository;
	private final BoardRepository boardRepository;
	
	
    // 반응 저장 메서드
    public boolean processReaction(Long boardId, String reactionType, String username) {
        BoardEntity board = boardRepository.findById(boardId).orElse(null);

        if (board != null) {
            // ReactionEntity 생성
            BoardReactionEntity reactionEntity = BoardReactionEntity.builder()
                    .username(username)
                    .reactionType(reactionType)
                    .board(board)
                    .build();

            // ReactionEntity 저장
            boardReactionRepository.save(reactionEntity);
            return true;
        }
        return false;
    }


	public BoardReactionEntity findByBoardIdAndUsername(Long boardId, String username) {
		// TODO Auto-generated method stub
		return boardReactionRepository.findByBoardIdAndUsername( boardId,  username);
	}

	@Transactional
	public Object deleteByBoardIdAndUsername(Long boardId, String username) {
		// TODO Auto-generated method stub
		return boardReactionRepository.deleteByBoardIdAndUsername( boardId,  username);
	}


	public void save(BoardReactionEntity reactionEntity) {
		// TODO Auto-generated method stub
		boardReactionRepository.save(reactionEntity);
	}


	public List<BoardReactionEntity> findByBoardAndReactionType(BoardEntity dto, String like) {
		return boardReactionRepository.findByBoardAndReactionType(dto, like);
	}


	
}