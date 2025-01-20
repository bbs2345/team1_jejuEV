package kr.co.mbc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReplyEntity;
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

	private Long Likes = 0L;
	private Long Dislikes = 0L;
	private BoardEntity board;
	private UserEntity user;

}
