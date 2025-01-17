package kr.co.mbc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_evchargerstation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EvChargingStationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String statId; // 충전소ID
    private String statNm; // 충전소이름
    private String addr; // 주소
    private String lat; // 위도
    private String lng; // 경도
    private String useTime; // 이용가능시간
    private String busiId; // 기관아이디
    private String busiNm; // 운영기관명
    private String busiCall; // 운영기관연락처
    private String zcode; // 지역코드
    private String parkingFree; // 주차료무료
    private String note; // 충전소 안내
    
    @OneToMany(mappedBy = "station")
    @JsonIgnore
    private List<EvChagerEntity> chargerList;

}
