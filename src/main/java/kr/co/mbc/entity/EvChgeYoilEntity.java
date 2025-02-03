package kr.co.mbc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_yoil_chger_count")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvChgeYoilEntity {
	
	@Id
	private Long id; 
	
	private String operatingInstitution; 
	private String evChargingStationId;
	private String evChargingStationName;
	private String evChargerId; 
	private String evChargerName;
	private String evChargerCategory;
	private String baseYearMonth;
	private String baseDayOfTheWeek;
	private int chargingCount;
	private String chargingTime;
	private String chargingAmount;
	private String chargingFee;
	

}