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
@Table(name = "tbl_evcharger")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvChargerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String chgerId; // 충전기ID
	private String chgerType; // 충전기타입
	private String stat; // 충전기 상태
	private String statUpdDt; // 상태갱신일시
	private String lastTsdt; // 마지막 충전시작일시
	private String lastTedt; // 마지막 충전종료일시
	private String nowTsdt; // 충전중 시작일시
	private String powerType; // 급속이냐 완속이냐?
	private String output; // 충전용량
	private String method; // 충전방식
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "stat_id")
	private EvStationEntity station;
	
}
