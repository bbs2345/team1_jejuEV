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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_evcharger") // 데이터베이스의 테이블 이름
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EvChagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String statNm; // 충전소 이름
    private String chgerId; // 충전기ID
    private String chgerType; // 충전기 타입
    private String addrDetail; // 주소상세
    private String location; // 상세위치
    private String bnm; // 기관명
    private String stat; // 충전기상태
    private String statUpdDt; // 상태갱신일시
    private String lastTsdt; // 마지막 충전시작일시
    private String lastTedt; // 마지막 충전종료일시
    private String nowTsdt; // 충전중 시작일시
    private String powerType; // 충전속도타입
    private String output; // 충전용량
    private String method; // 충전방식
    private String zscode; // 지역구분 상세 코드 
    private String kind; // 충전소 구분 코드 
    private String kindDetail; // 충전소 구분 상세코드 
    private String limitYn; // 이용자 제한
    private String limitDetail; // 이용제한 사유
    private String delYn; // 삭제 여부
    private String delDetail; // 삭제 사유
    private String trafficYn; // 편의제공 여부
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "station_id", nullable = false)
    private EvChargingStationEntity station;
    
    @Column(name = "stat_id")  // EvchargingStationEntity stat_id 컬럼
    private String statId;

}