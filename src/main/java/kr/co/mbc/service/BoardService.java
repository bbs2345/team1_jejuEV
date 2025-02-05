package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.AttachEntity;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final AttachService attachService;

	public BoardEntity save(BoardEntity boardEntity) {
		return boardRepository.save(boardEntity);
	}

	public List<BoardEntity> findAll() {
		List<BoardEntity> list = boardRepository.findAll();
		
		return list;
	}
	
	public BoardEntity findById(Long id) {
		Optional<BoardEntity> opt = boardRepository.findById(id);
		
		if(opt.isPresent()) {
			BoardEntity dto = opt.get();
			return dto;
		}
		
		return null;
	}
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		boardRepository.deleteById(id);
	}

	public void update(BoardEntity boardEntity) {
		// TODO Auto-generated method stub
		boardRepository.save(boardEntity);
	}

	public void save(BoardEntity entity, String fullFileName) {
		BoardEntity boardEntity = boardRepository.save(entity);
		AttachEntity attachEntity = AttachEntity.builder().filename(fullFileName).board(boardEntity).build();
		attachService.save(attachEntity);
	}

	public Long getTotalCount(Criteria criteria) {
		return boardRepository.getTotalCount(criteria);
	}

	public List<BoardEntity> findAll(Criteria criteria) {
		return boardRepository.findAll(criteria);
	}

	
	//========================================
	
//	public void likeBoard(Long boardId) {
//        BoardEntity board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//        board.setLikes(board.getLikes() + 1);  // 좋아요 수 증가
//        boardRepository.save(board);
//    }
//
//    public void dislikeBoard(Long boardId) {
//        BoardEntity board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//        board.setDislikes(board.getDislikes() + 1);  // 나빠요 수 증가
//        boardRepository.save(board);
//    }
//
//    public long getLikesCount(Long boardId) {
//        return boardRepository.findById(boardId).map(BoardEntity::getLikes).orElse(0L);
//    }
//
//    public long getDislikesCount(Long boardId) {
//        return boardRepository.findById(boardId).map(BoardEntity::getDislikes).orElse(0L);
//    }
//	
	//========================================

}
