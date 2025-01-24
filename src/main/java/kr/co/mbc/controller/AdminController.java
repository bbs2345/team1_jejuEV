package kr.co.mbc.controller;


import java.util.ArrayList;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.mbc.dto.BoardForm;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.dto.UserResponse;
import kr.co.mbc.entity.AttachEntity;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.CateEntity;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.service.AttachService;
import kr.co.mbc.service.BoardService;
import kr.co.mbc.service.CateService;
import kr.co.mbc.service.UserService;
import kr.co.mbc.utils.FormatDateUtil;
import kr.co.mbc.utils.Pagination;
import kr.co.mbc.utils.UploadFileUtils;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private static final String SERVICEPATH = "board";
	private final UserService userService;
	private final CateService cateService;
	private final AttachService attachService;
	private final FormatDateUtil formatDateUtil;
	private final BoardService boardService;
	private final UploadFileUtils uploadFileUtils;
	
	// 공지사항 입력 기능
	@PostMapping("/noticeInsert")
	public String noticeInsert(BoardForm boardForm, MultipartHttpServletRequest mRequest) throws Exception {
		
		UserEntity userEntity = userService.findByUsername(boardForm.getWriter());
		
		BoardEntity boardEntity = BoardEntity.toBoardEntity(boardForm);
		
		CateEntity cateEntity = cateService.findByCname("공지사항");
		
		boardEntity.setCate(cateEntity);
		boardEntity.setUser(userEntity);
		boardEntity.setCreateDate(formatDateUtil.getCurrentDate());
		boardEntity.setUpdateDate(formatDateUtil.getCurrentDate());
		
		boardService.save(boardEntity);
		
		
		MultipartFile multipartFile = mRequest.getFile("myfile");
		
		if (!multipartFile.isEmpty()) {
			String fullFileName = uploadFileUtils.uploadBoardFile(multipartFile, SERVICEPATH, boardEntity.getId());
			AttachEntity attachEntity = new AttachEntity(null, fullFileName, boardEntity);
			attachService.save(attachEntity);  // 새로 업로드된 파일 정보 저장
		}
		
		System.out.println(cateEntity.getCname());
		
		return "redirect:/board/"+ URLEncoder.encode(cateEntity.getCname(),"UTF-8")+ "/list";
	}
	
	// 공지사항 입력 화면
	@GetMapping("/noticeInsert")
	public String noticeInsert() {
		
		return "/board/insert";
	}
	
	// 관리자화면
	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		List<CateEntity> cateList = cateService.findAll();

		ServletContext context = request.getServletContext();
		context.setAttribute("cateList", cateList);
		
		return "/admin/home";
	}
	
	// 카테고리 추가화면
	@GetMapping("/cateInsert")
	public String cateInsert() {
			
		return "/admin/cateInsert";
	}
	
	// 회원목록
	@GetMapping("/userList")
	public String userList(Criteria criteria, Model model) {
		
		List<UserEntity> userEntities = userService.findMembers(criteria);
		
	    List<UserResponse> userList = new ArrayList<>();
	    for (UserEntity userEntity : userEntities) {
	        UserResponse userResponse = UserEntity.toUserResponse(userEntity);
	        userList.add(userResponse);
	    }
		
		Long totalCount = userService.getTotalCount(criteria);
		
		Pagination pagination = new Pagination(criteria, totalCount);
		
		model.addAttribute("userList", userList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("criteria", criteria);
		
		return "admin/userList";
	}
	
}
