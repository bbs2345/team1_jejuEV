package kr.co.mbc.dto;

import java.util.List;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReplyReactionEntity;
import kr.co.mbc.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyResponse {

	private Long id;
	private String content;

	private String writeDate;


	private BoardEntity board;
	private UserEntity user;
	private List<ReplyReactionEntity> replyReactionList;
	

}
