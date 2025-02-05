package kr.co.mbc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.mbc.dto.BoardForm;
import kr.co.mbc.dto.BoardResponse;
import kr.co.mbc.dto.Criteria;
import kr.co.mbc.dto.Pagination;
import kr.co.mbc.entity.AttachEntity;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.CateEntity;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.service.AttachService;
import kr.co.mbc.service.BoardReactionService;
import kr.co.mbc.service.BoardService;
import kr.co.mbc.service.CateService;
import kr.co.mbc.service.UserService;
import kr.co.mbc.utils.FormatDateUtil;
import kr.co.mbc.utils.UploadFileUtils;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private static final String SERVICEPATH = "board";
	
	private final AttachService attachService;
	
	private final BoardService boardService;
	
	private final UploadFileUtils uploadFileUtils;
	
	private final FormatDateUtil formatDateUtil;
	
	private final UserService userService;
	
	private final BoardReactionService reactionService;
	
	private final CateService cateService;
	
	
	@GetMapping("/insert400")
	public String insert400() {
		
		UserEntity userEntity = userService.findByUsername("m001");
		String currentDate = formatDateUtil.getCurrentDate();
		CateEntity cate1 = cateService.findByCid("free");
		CateEntity cate2 = cateService.findByCid("notice");
		CateEntity cate3 = cateService.findByCid("qna");
		
		for (int i = 1; i < 311; i++) {
			BoardEntity boardEntity = BoardEntity.builder()
					.cate(cate1)
					.title("자유글"+i)
					.content("QnA내용입니다.")
					.createDate(currentDate)
					.user(userEntity).build();
			boardService.save(boardEntity);
		}
		
		for (int i = 1; i < 311; i++) {
			BoardEntity boardEntity = BoardEntity.builder()
					.cate(cate2)
					.title("공지"+i)
					.content("QnA내용입니다.")
					.createDate(currentDate)
					.user(userEntity).build();
			boardService.save(boardEntity);
		}
		
		for (int i = 1; i < 311; i++) {
			BoardEntity boardEntity = BoardEntity.builder()
					.cate(cate3)
					.title("질문"+i)
					.content("QnA내용입니다.")
					.createDate(currentDate)
					.user(userEntity).build();
			boardService.save(boardEntity);
		}
		
		return "redirect:/board/list";
	}
	
	//보더 수정화면 이미지 삭제하기
	@PostMapping("/deleteBoardFile")
	@ResponseBody
	public String deleteBoardFile(@RequestParam Map<String, String> map) {
		
		String filename = map.get("filename");
		
	    uploadFileUtils.deleteFile(filename); // 파일 삭제 처리
	    attachService.deleteByFilename(filename); // attach 테이블에서 해당 filename 삭제 처리
	   
	    return "삭제완료";
	}
	
	//이미지 넣기
	@GetMapping("/imgDisplay")
	public ResponseEntity<byte[]> imgDisplay(String fullFileName) {
		ResponseEntity<byte[]> responseEntity = uploadFileUtils.imgDisplay(fullFileName);
		
		return responseEntity;
	}

	// 삭제 기능
	@PostMapping("/delete")
	public String delete(Long id) {
		BoardEntity boardEntity = boardService.findById(id);
		List<AttachEntity> fileList = boardEntity.getAttachList();
		
		for (AttachEntity file : fileList) {
			uploadFileUtils.deleteFile(file.getFilename());
		}
		
		boardService.delete(id);
		return "redirect:/board/list";
	}
	
	
	// 수정기능
	@PostMapping("/update")
	public String update(@ModelAttribute BoardForm boardForm, MultipartHttpServletRequest mRequest) {
		
		BoardEntity boardEntity = boardService.findById(boardForm.getId());
		
	    // 기존 파일 삭제 업로드된 파일 삭제, db에 삭제
	    List<AttachEntity> existingFiles = attachService.findByBoard(boardEntity);

	    MultipartFile multipartFile = mRequest.getFile("myfile");
	    
	    if (existingFiles != null && !existingFiles.isEmpty()) {
	        
	    	for (AttachEntity file : existingFiles) {
	            if (multipartFile != null && multipartFile.isEmpty()) {
	                // 새로 수정된 파일이 없는 경우 기존 파일 유지
	                continue;
	            }
	            uploadFileUtils.deleteFile(file.getFilename());
	            attachService.delete(file);  // 기존 파일 삭제 후 DB에서 삭제
	        }
	    }

	    // 새로운 파일 업로드
	    if (!multipartFile.isEmpty()) {
	    	String fullFileName = uploadFileUtils.uploadBoardFile(multipartFile, SERVICEPATH, boardEntity.getId());
	    	AttachEntity attachEntity = AttachEntity.builder()
	    			.filename(fullFileName)
	    			.board(boardEntity)
	    			.build();
	    	attachService.save(attachEntity);  // 새로 업로드된 파일 정보 저장
		}

	    boardEntity.setUpdateDate(formatDateUtil.getCurrentDate());
	    boardEntity.setContent(boardForm.getContent());

	    boardService.update(boardEntity);  // 수정된 게시글 저장

		return "redirect:/board/read/" + boardEntity.getId();
	}

	// 수정화면
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
	    
		BoardEntity boardEntity = boardService.findById(id);
	    
		if (boardEntity == null) {
	        return "redirect:/board/list";
	    }

	    BoardResponse boardResponse = BoardEntity.toBoardResponse(boardEntity);
	    model.addAttribute("boardResponse", boardResponse);

	    return "board/update";
	}


	// 읽기 기능
	@GetMapping({"/read/{id}" , "{cid}/read/{id}"})
	public String read(@PathVariable(value = "cid", required = false) String cid, @PathVariable("id") Long id, Model model, @ModelAttribute("criteria") Criteria criteria) {
		
		BoardEntity dto = boardService.findById(id);
		
		if (dto == null) {
			return "redirect:/board/list";
		}
		
		int likesCount = reactionService.findByBoardAndReactionType(dto, "like").size();
		int dislikesCount = reactionService.findByBoardAndReactionType(dto, "dislike").size();
		
		BoardResponse boardResponse = BoardEntity.toBoardResponse(dto);
		
		String con = dto.getContent();
		con = con.replace("\n", "<br>");
		dto.setContent(con);
		
		model.addAttribute("boardResponse", boardResponse);
		model.addAttribute("reactionCounts", Map.of("likes", likesCount, "dislikes", dislikesCount));
		
		return "board/read";
	}
	
	@GetMapping({"/list" , "/{cid}/list"})
	public String boardList(@PathVariable(value = "cid", required = false) String cid, @ModelAttribute("criteria") Criteria criteria, Model model) {
	    
		if (criteria.getType() == "writer" && criteria.getType() != null) {
			String username = criteria.getKeyword();
			UserEntity userEntity = userService.findByUsername(username);
			String uId = userEntity.getId().toString();
			criteria.setKeyword(uId);
		}
		
		if (cid != null) {
			criteria.setCateId(cid);
			CateEntity cateEntity = cateService.findByCid(cid);
			model.addAttribute("cateEntity", cateEntity);
		}
		
	    // 게시판 목록 가져오기
	    List<BoardEntity> boardEntities = boardService.findAll(criteria);  // `cid`와 `criteria`를 전달
	    
	    List<BoardResponse> boardList = new ArrayList<>();
	    for (BoardEntity boardEntity : boardEntities) {
	        boardList.add(BoardEntity.toBoardResponse(boardEntity));
	    }
	    
	    // 게시글 수 가져오기
	    Long totalCount = boardService.getTotalCount(criteria);

	    // Pagination 객체 생성
	    Pagination pagination = new Pagination(criteria, totalCount);

	    model.addAttribute("boardList", boardList);
	    model.addAttribute("pagination", pagination);
	    
	    return "board/list";
	}
	
	// 게시글 입력 기능
	@PostMapping("/{cid}/insert")
	public String insert(@PathVariable("cid")String cid, BoardForm boardForm, MultipartHttpServletRequest mRequest) throws Exception {
		
		UserEntity userEntity = userService.findByUsername(boardForm.getWriter());
		
		BoardEntity boardEntity = BoardEntity.toBoardEntity(boardForm);
		
		CateEntity cateEntity = new CateEntity();
		String formCid = boardForm.getCategory();  // 선택한 카테고리 cid 값 가져오기
		
		// cid가 없으면 사용자가 선택한 카테고리 값 사용
	    if (cid == null) {
	        cateEntity = cateService.findByCid(formCid);
	    } else {
	    	cateEntity = cateService.findByCid(cid);
		}
	    
		
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
		
		return "redirect:/board/"+ formCid + "/list";
	}

	// 게시글 입력 화면
	@GetMapping({"/insert", "/{cid}/insert"})
	public String insert(@PathVariable(value = "cid", required = false)String cid, Model model) {
		
		List<CateEntity> cateList = cateService.findAll();
		
		model.addAttribute("cateList", cateList);
		model.addAttribute("cid", cid);
		
		return "board/insert";
	}


}
