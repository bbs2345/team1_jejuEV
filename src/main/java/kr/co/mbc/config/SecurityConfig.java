package kr.co.mbc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.SavedRequest;

import kr.co.mbc.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomOAuth2UserService customOAuth2UserService;
	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		String[] permitUrls = {"/**", "/oauth2/**", "/login/**", "/logout/**", "/auth/**"};
		
//		http
//		.csrf((auth)-> auth.disable());
		
//		http
//		.formLogin((auth)-> auth.disable());
		
		// 로그아웃 설정
		http.logout((auth) -> auth
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/")
				.permitAll()
				);
		
		// 일반로그인 설정
		http
		.formLogin((auth)-> auth
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/login")
				.permitAll()
				);

		http
		.httpBasic((auth)-> auth.disable());
		
//		http
//		.oauth2Login(Customizer.withDefaults());
		
		// 네이버 로그인 설정
		http.oauth2Login((auth) -> auth
				.loginPage("/auth/oauth2login")
				.userInfoEndpoint((config) -> config
						.userService(customOAuth2UserService)
						)
				);
		
		// 권한 설정
		http.authorizeHttpRequests(
				(auth) -> auth
				.requestMatchers("/cate/**" ,"/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/**" , "/board/**", "/replies/**", "/boardReactions/**", "/replyReactions/**").hasAnyRole("ADMIN", "USER")
				.requestMatchers("/**").permitAll()
				);
		return http.build();
	}
}
