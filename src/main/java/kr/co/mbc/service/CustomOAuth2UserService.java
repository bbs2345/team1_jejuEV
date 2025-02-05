package kr.co.mbc.service;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import kr.co.mbc.utils.FormatDateUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;
	private final FormatDateUtil formatDateUtil;
	
	// 의존성 순환 오류(Circular Dependency) 발생으로 사용하려면 securityConfig에서 bcrypt bean을 빼고 새로운 configuration 환경에 추가해야함.
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		OAuth2Response oAuth2Response = null;
		
		if (registrationId.equals("naver")) {
			oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
		}
		
		String role = "ROLE_USER";
		
		String encodePass = bCryptPasswordEncoder.encode("Social_login");
		
//		String randomPass = createRandomString();
//		
//		String uuidPass = UUID.randomUUID().toString();
		
		// db 작업
		String username = oAuth2Response.getProvider()+"___mbc___" + oAuth2Response.getProviderId();
		
		UserEntity dbUserEntity = userRepository.findByUsername(username);
		
		String name = oAuth2Response.getName();
		
		String date = formatDateUtil.getCurrentDate();
		
		if(dbUserEntity == null) {
			dbUserEntity = UserEntity.builder().username(username).password(encodePass).name(name).role(role).createDate(date).build();
		}else {
			dbUserEntity.setName(name);
			dbUserEntity.setRole(role);
		}
		userRepository.save(dbUserEntity);
		
		return new CustomOAuth2User(oAuth2Response, role);
	}
	
	// 10자리 랜덤 문자열 생성(bcypt대신 사용)
//	private String createRandomString() {
//        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        
//        // 보안적으로 안전한 난수 생성
//        SecureRandom random = new SecureRandom();
//        StringBuilder sb = new StringBuilder(10);
//
//        for (int i = 0; i < 8; i++) {
//            int index = random.nextInt(CHARACTERS.length());
//            sb.append(CHARACTERS.charAt(index));
//        }
//
//        return sb.toString();
//    }
	
}
