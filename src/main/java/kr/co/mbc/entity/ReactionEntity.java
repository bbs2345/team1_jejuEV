package kr.co.mbc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.mbc.dto.ReactionResponse;
import kr.co.mbc.dto.ReplyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_reaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReactionEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String reactionType;
	
	@ManyToOne
	@JoinColumn(name = "board_id" , nullable = false)
	private BoardEntity board;
	
	//ReactionEntity를 Reactionresponse로 변환
	public static ReactionResponse toReactionResponse(ReactionEntity reactionEntity) {
		return ReactionResponse.builder()
				.id(reactionEntity.getId())
				.username(reactionEntity.getUsername())
				.reactionType(reactionEntity.getReactionType())
				.build();
	}
	
}