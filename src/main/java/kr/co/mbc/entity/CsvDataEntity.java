package kr.co.mbc.entity;

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
@Table(name = "tbl_csvdt")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CsvDataEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String statId;
	
	private String bnm; // 운영기관
	private String statNm; // 충전소 이름
	private String chgerId; // 충전기 ID
    private String chgerNm; // 충전기 이름
    private String chgerType; // 충전기 타입
    private String chgerStdt; // 충전 일자
    private String chgerCount; // 충전 횟수
    private String chgerTime; // 충전 시간
    private String chgerAm; // 충전량
    private String chgerCost; // 충전 금액
    
    
    
    
    
    
    
}
