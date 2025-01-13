package kr.co.mbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.AttachEntity;
import kr.co.mbc.entity.BoardEntity;

public interface AttachRepository extends JpaRepository<AttachEntity, Long>{

	List<AttachEntity> findByBoard(BoardEntity dto);

	List<AttachEntity> findByFilename(String filename);

}
