package kr.co.mbc.dto;

import java.util.List;

import kr.co.mbc.entity.AttachEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardResponse {
	
	private Long id;
	
	private String title;
	
	private String writer; // username
	
	private String content;
	
	private String createDate;
	
	private String updateDate;
	
	private List<AttachEntity> attachList;
	
}
