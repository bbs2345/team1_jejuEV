package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.mbc.dto.PageTO;
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

	



}

