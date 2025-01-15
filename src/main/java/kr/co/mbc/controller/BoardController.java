package kr.co.mbc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.ReverbType;

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
import kr.co.mbc.dto.UserResponse;
import kr.co.mbc.entity.AttachEntity;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.service.AttachService;
import kr.co.mbc.service.BoardService;
import kr.co.mbc.service.UserService;
import kr.co.mbc.utils.FormatDateUtil;
import kr.co.mbc.utils.Pagination;
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
	
	@GetMapping("/insert400")
	public String insert400() {
		
		UserEntity userEntity = userService.findByUsername("m001");
		String currentDate = formatDateUtil.getCurrentDate();
		
		for (int i = 1; i < 412; i++) {
			BoardEntity boardEntity = BoardEntity.builder()
					.title("제목"+i)
					.content("내용입니다.")
					.writer(userEntity.getUsername())
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

	    // 기존 파일 목록
	    List<AttachEntity> fileList = attachService.findByBoard(boardEntity);
	    model.addAttribute("fileList", fileList);

	    // 파일 미리보기 처리
	    if (!fileList.isEmpty()) {
	        String existingFileName = fileList.get(0).getFilename();
	        ResponseEntity<byte[]> preview = uploadFileUtils.previewImage(existingFileName);
	        model.addAttribute("preview", preview);
	    }

	    return "/board/update";
	}


	// 읽기 기능
	@GetMapping("/read/{id}")
	public String read(@PathVariable("id") Long id, Model model, Criteria criteria) {
		
		BoardEntity dto = boardService.findById(id);
		
		if (dto == null) {
			return "redirect:/board/list";
		}
		
		BoardResponse boardResponse = BoardEntity.toBoardResponse(dto);
		
		String con = dto.getContent();
		con = con.replace("\n", "<br>");
		dto.setContent(con);
		
		List<AttachEntity> fileList = attachService.findByBoard(dto);

		model.addAttribute("boardResponse", boardResponse);
		model.addAttribute("fileList", fileList);
		model.addAttribute("criteria", criteria);
		
		return "board/read";
	}
	
	@GetMapping("/list")
	public String boardList(Criteria criteria, Model model) {
		
		List<BoardEntity> boardEntities = boardService.findAll(criteria);
		
	    List<BoardResponse> boardList = new ArrayList<>();
	    for (BoardEntity boardEntity : boardEntities) {
	        BoardResponse boardResponse = BoardEntity.toBoardResponse(boardEntity);
	        boardList.add(boardResponse);
	    }
		
		Long totalCount = boardService.getTotalCount(criteria);
		
		Pagination pagination = new Pagination(criteria, totalCount);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("criteria", criteria);
		
		return "board/list";
	}

	/*
	// 목록 보기
	@GetMapping("/list")
	public void list(Model model) {
		
		List<BoardEntity> boardEntities = boardService.findAll();

		List<BoardResponse> boardList = new ArrayList<>();
		
		for (BoardEntity boardEntity : boardEntities) {
			BoardResponse boardResponse = BoardEntity.toBoardResponse(boardEntity);
			boardList.add(boardResponse);
		}

		model.addAttribute("boardResponseList", boardList);
	}
	*/

	// 게시글 입력 기능
	@PostMapping("/insert")
	public String insert(BoardForm boardForm, MultipartHttpServletRequest mRequest) {
		
		UserEntity userEntity = userService.findByUsername(boardForm.getWriter());
		
		BoardEntity boardEntity = BoardEntity.toBoardEntity(boardForm);
		
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
		
		return "redirect:/board/list";
	}

	// 게시글 입력 화면
	@GetMapping("/insert")
	public void insert() {
	}



}
