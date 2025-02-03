package kr.co.mbc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.mbc.dto.UserForm;
import kr.co.mbc.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 기본키
	
	@Column(nullable = false, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false)
	private String password; // 비밀번호
	
	@Column(nullable = false)
	private String name; // 이름
	
	private String createDate; // 가입일
	
	private String profileImage; // 프로필이미지
	
	private String role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<BoardEntity> boardList;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<ReplyEntity> replyList;
	
	
	// memberForm -> memberEntity로 변환
	public static UserEntity toUserEntity(UserForm userForm) {
		return UserEntity.builder()
				.username(userForm.getUsername())
				.password(userForm.getPassword())
				.name(userForm.getName())
				.role("ROLE_USER")
				.build();
	}

	// memberEntity -> memberResponse로 변환
	public static UserResponse toUserResponse(UserEntity userEntity) {
		return UserResponse.builder()
				.username(userEntity.getUsername())
				.name(userEntity.getName())
				.profileImage(userEntity.getProfileImage())
				.createDate(userEntity.getCreateDate())
				.build();
	}
	
}
