package kr.co.mbc.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.mbc.dto.CustomOAuth2User;
import kr.co.mbc.dto.NaverResponse;
import kr.co.mbc.dto.OAuth2Response;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		OAuth2Response oAuth2Response = null;
		
		if (registrationId.equals("naver")) {
			oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
		}
		
		String role = "ROLE_USER";
		
		// db 작업
		String username = oAuth2Response.getProvider()+"___mbc___" + oAuth2Response.getProviderId();
		
		UserEntity dbUserEntity = userRepository.findByUsername(username);
		
		String name = oAuth2Response.getName();
		
		if(dbUserEntity == null) {
			dbUserEntity = UserEntity.builder().username(username).name(name).role(role).build();
		}else {
			dbUserEntity.setName(name);
			dbUserEntity.setRole(role);
		}
		userRepository.save(dbUserEntity);
		
		return new CustomOAuth2User(oAuth2Response, role);
	}
}
