package kr.co.mbc.dto;

import java.util.Map;

public class NaverResponse implements OAuth2Response {

	private final Map<String, Object> attributes;
	
	// 네이버는 사용자생성자를 따로 설정해줘야함.
	@SuppressWarnings("unchecked")
	public NaverResponse(Map<String, Object> attributes) {
		System.out.println(attributes);
		this.attributes = (Map<String, Object>)attributes.get("response");
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "naver";
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return attributes.get("id").toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return attributes.get("name").toString();
	}


}


