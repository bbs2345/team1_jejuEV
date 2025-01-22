package kr.co.mbc.dto;

import kr.co.mbc.entity.ReplyEntity;
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
@ToString
@Builder
public class ReplyReactionResponse {

	private Long id;
	private String username;
	private String reactionType;
	private ReplyEntity reply;

}
