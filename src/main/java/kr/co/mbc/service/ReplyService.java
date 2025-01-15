package kr.co.mbc.service;

import java.util.List;

import org.springframework.stereotype.Service;


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



	public List<ReplyEntity> findByBoardId(Long bId) {
		// TODO Auto-generated method stub
		return replyRepository.findByBoardId(bId);
	}



	public void deleteById(Long id) {
		replyRepository.deleteById(id);
	}
	


}

