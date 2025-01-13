package kr.co.mbc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.mbc.entity.AttachEntity;
import kr.co.mbc.entity.AttachEntity.AttachEntityBuilder;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.repository.AttachRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AttachService {
	
	private final AttachRepository attachRepository;

	public void save(AttachEntity attachEntity) {
		attachRepository.save(attachEntity);
	}

	public List<AttachEntity> findByBoard(BoardEntity dto) {
		List<AttachEntity> fileList = attachRepository.findByBoard(dto);
		return fileList;
	}

	public void delete(AttachEntity file) {
		// TODO Auto-generated method stub
		attachRepository.delete(file);
	}

	public void deleteByFilename(String filename) {
		List<AttachEntity> attachments = attachRepository.findByFilename(filename);
	    if (attachments != null && !attachments.isEmpty()) {
	        for (AttachEntity attachEntity : attachments) {
	            attachRepository.delete(attachEntity);  // 기존 파일 삭제 후 DB에서 삭제
	        }
	    }
	}


}
