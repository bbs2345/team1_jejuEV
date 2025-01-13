package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.MemberEntity;
import kr.co.mbc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void save(MemberEntity memberEntity) {
		memberRepository.save(memberEntity);
	}

	public MemberEntity findByUsername(String username) {
		return memberRepository.findByUsername(username);
	}

	@Transactional
	public void deleteByUsername(String username) {
		memberRepository.deleteByUsername(username);
	}

	public List<MemberEntity> findMembers(Criteria criteria) {
		
		return memberRepository.findMembers(criteria);
	}

	public MemberEntity findById(Long i) {
		Optional<MemberEntity> opt = memberRepository.findById(i);
		
		if (opt.isPresent()) {
			MemberEntity memberEntity = opt.get();
			return memberEntity;
		}
		return null;
	}

	public Long getTotalCount(Criteria criteria) {
		return memberRepository.getTotalCount(criteria);
	}
	
}
