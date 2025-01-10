package kr.co.mbc.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.validation.Valid;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.dto.MemberForm;
import kr.co.mbc.dto.MemberResponse;
import kr.co.mbc.entity.MemberEntity;
import kr.co.mbc.service.MemberService;
import kr.co.mbc.utils.UploadFileUtils;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private static final String SERVICENAME ="member";
	
	private final UploadFileUtils uploadFileUtils;

	private final MemberService memberService;
	
	@GetMapping("/insert400")
	public String insert400() {
		
		for (int i=1; i < 412; i++) {
			MemberEntity memberEntity = MemberEntity.builder()
					.username("user"+i)
					.password("1")
					.name("test")
					.build();
			
			memberService.save(memberEntity);
		}
		
		return "redirect:/admin/memberList";
	}
	
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
	
	// 아이디 중복체크
	@PostMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam Map<String, String> map) {
		
		String username = map.get("username");
		
		MemberEntity dbEntity = memberService.findByUsername(username);
		
		if(dbEntity == null) {
			return "ok";
		}
		
		return "no";
	}
	
	// 회원삭제
	@PostMapping("/delete")
	public String username(String username) {
		
		MemberEntity memberEntity = memberService.findByUsername(username);
		
		String profileImage = memberEntity.getProfileImage();

		if(profileImage != null) {
			uploadFileUtils.deleteFile(profileImage);
		}
		
		memberService.deleteByUsername(username);
		
		return "redirect:/";
	}
	
	//회원정보 수정 처리
	@PostMapping("/update")
	public String update(MemberForm memberForm, MultipartHttpServletRequest mRequest) {
		
		MemberEntity memberEntity = memberService.findByUsername(memberForm.getUsername());
		memberEntity.setName(memberForm.getName());
		
		MultipartFile multipartFile = mRequest.getFile("profileImage");
		
		if (!multipartFile.isEmpty()) {
			String oldFileName = memberEntity.getProfileImage();
			
			if (!oldFileName.isEmpty()) {
				uploadFileUtils.deleteFile(oldFileName);
			}
			
			String newFileName = uploadFileUtils.uploadFile(multipartFile, SERVICENAME, memberForm.getUsername());
			memberEntity.setProfileImage(newFileName);
		}
		
		memberService.save(memberEntity);
		
		return "redirect:/member/read/"+memberForm.getUsername();
	}

	// 회원정보 수정 페이지
	@GetMapping("/updateForm/{username}")
	public String updateForm(@PathVariable("username") String username, Model model) {

		MemberEntity memberEntity = memberService.findByUsername(username);

		MemberResponse memberResponse = MemberEntity.toMemberResponse(memberEntity);
		
		model.addAttribute("memberResponse", memberResponse);

		return "member/updateForm";
	}

	// 회원정보 상세보기 페이지
	@GetMapping("/read/{username}")
	public String read(@PathVariable("username") String username, Model model, Criteria criteria) {

		MemberEntity memberEntity = memberService.findByUsername(username);

		MemberResponse memberResponse = MemberEntity.toMemberResponse(memberEntity);

		model.addAttribute("memberResponse", memberResponse);
		model.addAttribute("criteria", criteria);

		return "member/read";
	}

	// 회원가입 처리
	@PostMapping("/insert")
	public String insert(@Valid @ModelAttribute("memberForm") MemberForm memberForm, BindingResult bindingResult, MultipartHttpServletRequest mRequest) {
		
		if(bindingResult.hasErrors()) {
			return "member/insertForm";
		}
		
		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberForm);
		
		MultipartFile multipartFile = mRequest.getFile("profileImage");
		
		if (!multipartFile.isEmpty()) {
			String fileName = uploadFileUtils.uploadFile(multipartFile, SERVICENAME, memberForm.getUsername());
			memberEntity.setProfileImage(fileName);
		}

		memberService.save(memberEntity);

		return "redirect:/member/read/"+memberForm.getUsername();
	}

	// 회원가입 페이지
	@GetMapping("/insertForm")
	public String insertForm(MemberForm memberForm) {
		return "member/insertForm";
	}

}
