package kr.co.mbc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.mbc.dto.MemberForm;
import kr.co.mbc.dto.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 기본키
	
	@Column(nullable = false, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false)
	private String password; // 비밀번호
	
	@Column(nullable = false)
	private String name; // 이름
	
	private String profileImage; // 프로필이미지
	
	
	
	
	
	
	
	
	// memberForm -> memberEntity로 변환
	public static MemberEntity toMemberEntity(MemberForm memberForm) {
		return MemberEntity.builder()
				.username(memberForm.getUsername())
				.password(memberForm.getPassword())
				.name(memberForm.getName())
				.build();
	}

	// memberEntity -> memberResponse로 변환
	public static MemberResponse toMemberResponse(MemberEntity memberEntity) {
		return MemberResponse.builder()
				.username(memberEntity.getUsername())
				.name(memberEntity.getName())
				.profileImage(memberEntity.getProfileImage())
				.build();
	}
	
}
