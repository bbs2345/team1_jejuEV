package kr.co.mbc.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
	
}
