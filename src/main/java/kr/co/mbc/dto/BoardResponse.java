package kr.co.mbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponse {
	private Long id;
	private String title;
	private String writer;
	private String content;
	private String createDate;
	private String updateDate;
	

	@Override
	public String toString() {
		return "BoardResponse [id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
	
}
