package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.mbc.entity.CateEntity;
import kr.co.mbc.repository.CateRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CateService {

	private final CateRepository cateRepository;

	public void save(CateEntity cateEntity) {
		cateRepository.save(cateEntity);
		
	}

	public List<CateEntity> findAll() {
		
		return cateRepository.findAll();
	}
	
	public CateEntity findByCname(String cname) {
		return cateRepository.findByCname(cname);
	}
	
	@Transactional
	public void delete(CateEntity cateEntity) {
		cateRepository.delete(cateEntity);
	}

	public CateEntity findByCid(String cid) {
		return cateRepository.findByCid(cid);
	}


}
