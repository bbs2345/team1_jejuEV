package kr.co.mbc.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.mbc.entity.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private final UserEntity userEntity;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		
		collection.add(new GrantedAuthority() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				System.out.println(userEntity.getRole());
				return userEntity.getRole();
			}
		});
		
		return collection;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEntity.getUsername();
	}

	public String getRole() {
		return userEntity.getRole();
	}
	
	public String getName() {
		return userEntity.getName();
	}
	
//	public UserEntity getUserEntity() {
//		// TODO Auto-generated method stub
//		return userEntity;
//	}
	
	

}
