package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.AttachEntity;
import kr.co.mbc.entity.AttachEntity.AttachEntityBuilder;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.repository.BoardRepository;
import kr.co.mbc.utils.UploadFileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final AttachService attachService;
	private final UploadFileUtils uploadFileUtils;

	public void save(BoardEntity boardEntity) {
		boardRepository.save(boardEntity);
	}

	public List<BoardEntity> findAll() {
		// TODO Auto-generated method stub
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


}
