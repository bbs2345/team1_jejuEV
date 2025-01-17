package kr.co.mbc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_csv")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CsvDataEntity {
	
	private String bnm;

}
