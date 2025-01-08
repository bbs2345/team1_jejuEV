package kr.co.mbc.service;

import org.springframework.stereotype.Service;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	public void save(BoardEntity boardEntity) {
		// TODO Auto-generated method stub
		boardRepository.save(boardEntity);
	}
}
