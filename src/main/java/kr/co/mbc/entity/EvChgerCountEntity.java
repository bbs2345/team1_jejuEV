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
@Table(name = "tbl_user_cate_chger_counts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvChgerCountEntity {
	
	@Id
	private Long id;
	
	private String operatingInstitution;
	
	private String baseDate;
	
	private String weather;
	
	private String evChargingStationId;
	
	private String evChargingStationName;
	
	private String evChargingStationAddress;
	
	private String carCategoryLarge;
	
	private String userCategory;
	
	private String userAgeRange;
	
	private String userGender;
	
	private String waitingCategory;
	
	private String evChargerCategory;
	
	private String waitingCount;
	
	private String chargingCount;
	
	private String waitingTimeTotal;
	
	private String chargingTimeTotal;
	
	private String leavingTimeTotal;
	
	
	
	
	
    
}   
    