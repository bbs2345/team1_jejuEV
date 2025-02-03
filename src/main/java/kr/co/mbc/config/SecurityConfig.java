package kr.co.mbc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
//	private final CustomerOAuth2UserService customerOAuth2UserService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		String[] permitUrls = {"/**", "/oauth2/**", "/login/**", "/logout/**", "/auth/**"};
		
//		http
//		.csrf((auth)-> auth.disable());
		
//		http
//		.formLogin((auth)-> auth.disable());
		
		http.logout((auth) -> auth
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/")
				.permitAll()
				);
		
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
		
//		http
//		.oauth2Login((auth)-> auth
//				.loginPage("/auth/login")
//				.userInfoEndpoint((config)-> config
//						.userService(customerOAuth2UserService)
//						)
//		);
		
		http.authorizeHttpRequests(
				(auth) -> auth
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/board/**, /replies/**, /boardReactions/**,/replyReactions/**").hasAnyRole("ADMIN", "USER")
				.requestMatchers("/**").permitAll()
				);
		return http.build();
	}
}
