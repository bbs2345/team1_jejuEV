package kr.co.mbc.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.mbc.dto.UserForm;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.service.UserService;
import kr.co.mbc.utils.FormatDateUtil;
import kr.co.mbc.utils.UploadFileUtils;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	private static final String SERVICEPATH = "user";

	private final UploadFileUtils uploadFileUtils;
	
	private final FormatDateUtil formatDateUtil;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/";
	}

	// 로그인 처리
	@PostMapping("/login")
	public String login(String username, String password, HttpSession session) {
		
		UserEntity userEntity = userService.findByUsername(username);
		
		if (userEntity == null || !bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
	        // 사용자 없음 또는 비밀번호 불일치
	        return "auth/loginForm";
	    }
		
		return "redirect:/";
	}

	// 로그인 페이지
	@GetMapping("/loginForm")
	public String loginForm() {

		return "auth/loginForm";
	}

	// 아이디 중복체크
	@PostMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam Map<String, String> map) {

		String username = map.get("username");

		UserEntity dbEntity = userService.findByUsername(username);

		if (dbEntity == null) {
			return "ok";
		}

		return "no";
	}

	// 회원가입 처리
	@PostMapping("/join")
	public String insert(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult,
			MultipartHttpServletRequest mRequest) {

		if (bindingResult.hasErrors()) {
			return "auth/joinForm";
		}

		UserEntity userEntity = UserEntity.toUserEntity(userForm);

		MultipartFile multipartFile = mRequest.getFile("profileImage");

		if (!multipartFile.isEmpty()) {
			String fileName = uploadFileUtils.uploadFile(multipartFile, SERVICEPATH, userForm.getUsername());
			userEntity.setProfileImage(fileName);
		}
		
		userEntity.setCreateDate(formatDateUtil.getCurrentDate());
		userService.save(userEntity);

		return "redirect:/user/read/" + userForm.getUsername();
	}

	// 회원가입 페이지
	@GetMapping("/joinForm")
	public String joinForm(UserForm userForm) {

		return "auth/joinForm";
	}

}
