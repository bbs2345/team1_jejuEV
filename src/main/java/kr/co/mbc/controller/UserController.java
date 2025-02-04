package kr.co.mbc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.dto.UserForm;
import kr.co.mbc.dto.UserResponse;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.service.UserService;
import kr.co.mbc.utils.UploadFileUtils;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private static final String SERVICEPATH ="user";
	
	private final UploadFileUtils uploadFileUtils;

	private final UserService userService;
	
	/*
	@GetMapping("/insert400")
	public String insert400() {
		
		for (int i=1; i < 412; i++) {
			UserEntity userEntity = UserEntity.builder()
					.username("user"+i)
					.password("1")
					.name("test")
					.build();
			
			userService.save(userEntity);
		}
		
		return "redirect:/admin/memberList";
	}
	*/
	
	
	// 회원 프로필이미지 불러오기
	@GetMapping("/imgDisplay")
	public ResponseEntity<byte[]> imgDisplay(String fullFileName) {
		
		/*
		// 마지막 '/'의 인덱스를 찾고, 파일네임만 추출
        String fileName = fullFileName.substring(fullFileName.lastIndexOf("/") + 1);
        
        // 마지막 '/'의 인덱스를 찾고, 경로만 추출
        String filePath = fullFileName.substring(0, fullFileName.lastIndexOf("/"));
		*/
		ResponseEntity<byte[]> entity = uploadFileUtils.imgDisplay(fullFileName);
        
		return entity;
	}
	
	// 회원삭제
	@PostMapping("/delete")
	public String username(String username, Authentication authentication) {
		
		UserEntity userEntity = userService.findByUsername(username);
		
		String profileImage = userEntity.getProfileImage();

		if(profileImage != null) {
			uploadFileUtils.deleteFile(profileImage);
		}
		
		userService.deleteByUsername(username);
		
		return "redirect:/auth/logout";
	}
	
	//회원정보 수정 처리
	@PostMapping("/update")
	public String update(UserForm userForm, MultipartHttpServletRequest mRequest) {
		
		UserEntity userEntity = userService.findByUsername(userForm.getUsername());
		userEntity.setName(userForm.getName());
		
		MultipartFile multipartFile = mRequest.getFile("profileImage");
		
		if (!multipartFile.isEmpty()) {
			
			String oldFileName = userEntity.getProfileImage();
			
			if (oldFileName != null) {
				uploadFileUtils.deleteFile(oldFileName);
			}
			
			String newFileName = uploadFileUtils.uploadFile(multipartFile, SERVICEPATH, userForm.getUsername());
			userEntity.setProfileImage(newFileName);
			
		}
		
		userService.save(userEntity);
		
		return "redirect:/user/read/"+userForm.getUsername();
	}

	// 회원정보 수정 페이지
	@GetMapping("/updateForm/{username}")
	public String updateForm(@PathVariable("username") String username, Model model) {

		UserEntity userEntity = userService.findByUsername(username);

		UserResponse userResponse = UserEntity.toUserResponse(userEntity);
		
		model.addAttribute("userResponse", userResponse);

		return "user/updateForm";
	}

	// 회원정보 상세보기 페이지
	@GetMapping("/read/{username}")
	public String read(@PathVariable("username") String username, Model model, Criteria criteria) {

		UserEntity memberEntity = userService.findByUsername(username);

		UserResponse userResponse = UserEntity.toUserResponse(memberEntity);

		model.addAttribute("userResponse", userResponse);
		model.addAttribute("criteria", criteria);

		return "user/read";
	}

	

}
