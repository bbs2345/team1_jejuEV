package kr.co.mbc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_attach")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachEntity {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	private String filename;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "board_id", nullable = false)
	private BoardEntity board;


	
	
}
