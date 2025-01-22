package kr.co.mbc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.mbc.dto.BoardReactionResponse;
import kr.co.mbc.dto.ReplyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_board_reaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BoardReactionEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String reactionType;
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	private BoardEntity board;
	
	//ReactionEntity를 Reactionresponse로 변환
	public static BoardReactionResponse toBoardReactionResponse(BoardReactionEntity boardReactionEntity) {
		return BoardReactionResponse.builder()
				.id(boardReactionEntity.getId())
				.username(boardReactionEntity.getUsername())
				.reactionType(boardReactionEntity.getReactionType())
				.board(boardReactionEntity.getBoard())
				.build();
	}
	
}