package kr.co.mbc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.mbc.dto.ReplyReactionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_reply_reaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReplyReactionEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String reactionType;
	
	@ManyToOne
	@JoinColumn(name = "reply_id")
	private ReplyEntity reply;
	
	//ReactionEntity를 Reactionresponse로 변환
	public static ReplyReactionResponse toReplyReactionResponse(ReplyReactionEntity replyReactionEntity) {
		return ReplyReactionResponse.builder()
				.id(replyReactionEntity.getId())
				.username(replyReactionEntity.getUsername())
				.reactionType(replyReactionEntity.getReactionType())
				.reply(replyReactionEntity.getReply())
				.build();
	}
	
}