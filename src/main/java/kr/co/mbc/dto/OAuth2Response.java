package kr.co.mbc.dto;

public interface OAuth2Response {

	// 네이버인지 구글인지?
	public String getProvider();
	
	// 네이버가 발급해주는 아이디, 이메일, 이름
	public String getProviderId();
	
	public String getName();
	
}
