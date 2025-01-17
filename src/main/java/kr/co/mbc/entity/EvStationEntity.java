package kr.co.mbc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_evstation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvStationEntity {
	
	@Id
	private String statId; // 충전소ID
	
	private String statNm; // 충전소명
	private String addr; // 충전기주소
	private String addrDetail; // 주소상세
	private String location; // 상세위치
	private String useTime; // 이용가능시간
	private String lat; // 위도
	private String lng; // 경도
	private String busiId; // 기관아이디
	private String bnm; // 기관명
	private String busiNm; // 운영기관명
	private String busiCall; // 운영기관 연락처
	private String zcode; // 지역코드
	private String zscode; // 지역구분 상세코드
	private String kind; // 충전소 구분코드
	private String kindDetail; // 충전소 구분 상세코드
	private String parkingFree; // 주차료 무료여부
	private String note; // 충전소 안내
	private String limitYn; // 이용자제한
	private String limitDetail; // 이용제한 사유
	private String delYn; // 삭제여부
	private String delDetail; // 삭제사유
	private String trafficYn; // 편의제공 여부
	
	@OneToMany(mappedBy = "station", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<EvChargerEntity> chargerList;

}
