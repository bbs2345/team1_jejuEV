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
import lombok.ToString;

@Entity
@Table(name = "tbl_cate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CateEntity {

	@Id
	private String cid;
	
	private String cname;
	
	public CateEntity(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	@OneToMany(mappedBy = "cate", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<BoardEntity> boardList;
	
}
