package kr.co.mbc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.mbc.dto.BoardResponse;
import kr.co.mbc.dto.ReplyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_reply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReplyEntity {
    
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
	private String writer; // 나중에 없앨것, 작성자는 user에서 가져오기
    
	@Column(length = 300, nullable = false)
	private String content;
	
	private String writeDate;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "board_id", nullable = false)
	private BoardEntity board;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;


	
	//replyEntity를 replyresponse로 변환
	public static ReplyResponse toReplyResponse(ReplyEntity replyEntity) {
		return ReplyResponse.builder()
				.id(replyEntity.getId())
				.content(replyEntity.getContent())
				.writeDate(replyEntity.getWriteDate())

				.board(replyEntity.getBoard())
				.user(replyEntity.getUser())
				.build();
	}

}
