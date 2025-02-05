package kr.co.mbc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import kr.co.mbc.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		// 로그아웃 설정
		http.logout((auth) -> auth
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/")
				.permitAll());
		
		// 일반로그인 설정
		http.formLogin((auth)-> auth
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/login")
				.permitAll());

		http.httpBasic((auth)-> auth.disable());
		
		// 네이버 로그인 설정
		http.oauth2Login((auth) -> auth
				.loginPage("/auth/oauth2login")
				.userInfoEndpoint((config) -> config.userService(customOAuth2UserService)));
		
		// 권한 설정
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/cate/**" ,"/admin/**").hasRole("ADMIN")
<<<<<<< HEAD
				.requestMatchers("/user/**" , "/replies/**", "/boardReactions/**", "/replyReactions/**").hasAnyRole("ADMIN", "USER")
=======
				.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
>>>>>>> 874d2df (수정완료)
				.requestMatchers("/**").permitAll()
				);
		
		return http.build();
	}
}
