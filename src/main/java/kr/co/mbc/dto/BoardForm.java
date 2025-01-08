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
public class BoardForm {
	private String title;
	private String writer;
	private String content;
	
	@Override
	public String toString() {
		return "BoardForm [title=" + title + ", writer=" + writer + ", content=" + content + "]";
	}

	
}
