package kr.co.mbc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int page; // 현재페이지
	private int perPageContent; //한페이지에 표시할 게시물 수
	
	private String cateId;
	
	private String type; // 검색 타입 username, name, 만약에 보드다 title, writer
	private String keyword; // 검색 키워드
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int page, int perPageContent) {
		this.page = page;
		this.perPageContent = perPageContent;
	}
	
	
	public int getOffset() {
		return (this.page-1)*this.perPageContent;
	}
	
}
