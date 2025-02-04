package kr.co.mbc.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

	private final OAuth2Response oAuth2Response;
	private final String role;
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		
		collection.add(new GrantedAuthority() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return role;
			}
		});
		return collection;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return oAuth2Response.getName();
	}
	
	public String getRole() {
		return role;
	}
	
	public String getUsername() {
		// id 대신 email을 써도 됨.
		return oAuth2Response.getProvider()+"___mbc___"+oAuth2Response.getProviderId();
	}
	
}
