package kr.co.mbc.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.mbc.dto.CustomUserDetails;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	
//	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//	UserEntity userEntity = customUserDetails.getUserEntity();
//	session.setAttribute("userEntity", userEntity);  // 세션에 userEntity 저장

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
										
		UserEntity userEntity = userRepository.findByUsername(username);
		
		if(userEntity != null) {
			return new CustomUserDetails(userEntity);
		}
		
		return null;
	}

}
