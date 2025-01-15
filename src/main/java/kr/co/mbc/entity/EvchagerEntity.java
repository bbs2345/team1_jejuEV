package kr.co.mbc.entity;



import org.springframework.data.annotation.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_ev_charger") // 데이터베이스의 테이블 이름
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvchagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String chgerId; // 충전기ID
    private String statId; // 충전소ID
    private String statNm; // 충전소이름
    private String chgerType; // 충전기 타입
    private String addr; // 주소
    private String addrDetail; // 주소상세
    private String location; // 상세위치
    private String lat; // 위도
    private String lng; // 경도
    private String useTime; // 이용가능시간
    private String busiId; // 기관아이디
    private String bnm; // 기관명
    private String busiNm; // 운영기관명
    private String busiCall; // 운영기관연락처
    private String stat; // 충전기상태
    private String statUpdDt; // 상태갱신일시
    private String lastTsdt; // 마지막 충전시작일시
    private String lastTedt; // 마지막 충전종료일시
    private String nowTsdt; // 충전중 시작일시
    private String powerType; // 충전속도타입
    private String output; // 충전용량
    private String method; // 충전방식
    private String zcode; // 충전방식
    private String zscode; // 지역구분 상세 코드 
    private String kind; // 충전소 구분 코드 
    private String kindDetail; // 충전소 구분 상세코드 
    private String parkingFree; // 주차료무료 
    private String note; // 충전소 안내 
    private String limitYn; // 이용자 제한
    private String limitDetail; // 이용제한 사유
    private String delYn; // 삭제 여부
    private String delDetail; // 삭제 사유
    private String trafficYn; // 편의제공 여부
    
//    @Transient
//    private int totalCount;
    

    


}