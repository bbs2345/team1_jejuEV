package kr.co.mbc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

	@NotBlank(message = "username은 필수 항목입니다.")
	private String username;
	
	@NotBlank(message = "password은 필수 항목입니다.")
	private String password;
	
	@NotBlank(message = "password2은 필수 항목입니다.")
	private String password2;

	@NotBlank(message = "name은 필수 항목입니다.")
	private String name;
	
	
}
