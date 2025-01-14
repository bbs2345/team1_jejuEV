package kr.co.mbc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.mbc.dto.BoardForm;
import kr.co.mbc.dto.BoardResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_board")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 60)
	private String title;
	
	@Column(length = 30, nullable = false)
	private String writer;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	private String createDate;
	private String updateDate;
	
	
	
	@OneToMany
	@JsonIgnore
	private List<AttachEntity> attachEntity;
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<ReplyEntity> replyList;
	
	@Override
	public String toString() {
		return "BoardEntity [id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	//boardForm을 BoardEntity 변형
	public static BoardEntity toBoardEntity(BoardForm boardForm) {
		return BoardEntity.builder()
				.title(boardForm.getTitle())
				.writer(boardForm.getWriter())
				.content(boardForm.getContent())
				.build();
	}
	//BoardEntity를 BoardResponse 변환
	public static BoardResponse toBoardResponse(BoardEntity boardEntity) {
		return BoardResponse.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.writer(boardEntity.getWriter())
				.content(boardEntity.getContent())
				.createDate(boardEntity.getCreateDate())
				.updateDate(boardEntity.getUpdateDate())
				.build();
	}
	
	
	
	
}
