package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.mbc.dto.PageTO;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReplyEntity;
import kr.co.mbc.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyRepository replyRepository;


	
	public void save(ReplyEntity reply) {
		replyRepository.save(reply);
		
	}



	public Page<ReplyEntity> findByBoardIdOrderByWriteDateDesc(Long bId, int page) {
        // Pageable 객체를 생성하여 페이징 처리된 댓글 목록을 조회
        Pageable pageable = PageRequest.of(page, 5);
        return replyRepository.findByBoardIdOrderByWriteDateDesc(bId, pageable);
    }



	public void deleteById(Long id) {
		replyRepository.deleteById(id);
	}



    public Optional<ReplyEntity> findById(Long id) {
        return replyRepository.findById(id);
    }



	public List<ReplyEntity> findByBoard(BoardEntity boardEntity) {
		return replyRepository.findByBoardId(boardEntity);
	}

	
	//========================================
	
	 // 댓글 좋아요/싫어요 처리 메서드
	
	
//	public void likeBoard(Long id) {
//        ReplyEntity replyEntity = replyRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//        replyEntity.setLikes(replyEntity.getLikes() + 1);  // 좋아요 수 증가
//        replyRepository.save(replyEntity);
//    }
//
//    public void dislikeBoard(Long id) {
//    	ReplyEntity replyEntity = replyRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//    	replyEntity.setDislikes(replyEntity.getDislikes() + 1);  // 나빠요 수 증가
//        replyRepository.save(replyEntity);
//    }
//
//    public long getLikesCount(Long boardId) {
//        return replyRepository.findById(boardId).map(ReplyEntity::getLikes).orElse(0L);
//    }
//
//    public long getDislikesCount(Long boardId) {
//        return replyRepository.findById(boardId).map(ReplyEntity::getDislikes).orElse(0L);
//    }


	
	//========================================



}

