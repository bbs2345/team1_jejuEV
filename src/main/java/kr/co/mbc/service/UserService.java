package kr.co.mbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void save(UserEntity userEntity) {
		userRepository.save(userEntity);
	}

	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	public void deleteByUsername(String username) {
		userRepository.deleteByUsername(username);
	}

	public List<UserEntity> findMembers(Criteria criteria) {
		
		return userRepository.findMembers(criteria);
	}

	public UserEntity findById(Long i) {
		Optional<UserEntity> opt = userRepository.findById(i);
		
		if (opt.isPresent()) {
			UserEntity userEntity = opt.get();
			return userEntity;
		}
		return null;
	}

	public Long getTotalCount(Criteria criteria) {
		return userRepository.getTotalCount(criteria);
	}

	public UserEntity findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
}
