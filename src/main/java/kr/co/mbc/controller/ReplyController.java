package kr.co.mbc.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReplyEntity;
import kr.co.mbc.entity.UserEntity;
import kr.co.mbc.service.BoardService;
import kr.co.mbc.service.ReplyService;
import kr.co.mbc.service.UserService;
import kr.co.mbc.utils.FormatDateUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;
	private final BoardService boardService;
	private final UserService userService;

	private final FormatDateUtil formatDateUtil;

	// 댓글 삭제
	@DeleteMapping("/")
	public String deleteById(@RequestBody Map<String, String> map) {
		
		Long id = Long.parseLong((String)map.get("rId"));

		replyService.deleteById(id);
		return "ok";
	}

    // 댓글 수정
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
	    Optional<ReplyEntity> optionalReply = replyService.findById(id);  // 댓글 존재 확인

	    if (optionalReply.isPresent()) {
	        ReplyEntity replyEntity = optionalReply.get();

	        String content = map.get("content");
	        if (content != null && !content.trim().isEmpty()) {
	            replyEntity.setContent(content.trim());
	            
	            replyEntity.setWriteDate(formatDateUtil.getCurrentDate());
	            
	            replyService.save(replyEntity);  // 수정된 댓글 저장
	        }
	    }
	    return "ok";
	}


        
 

	// 댓글 리스트 (페이징 처리)
    @GetMapping("/{bId}")
    public Page<ReplyEntity> list(@PathVariable("bId") Long bId, @RequestParam("page") int page) {
       
       page = page -1;

        // 페이징 처리된 댓글 목록 반환
       Page<ReplyEntity> paging = replyService.findByBoardIdOrderByWriteDateDesc(bId, page);
       
       System.out.println("================================================================");
       System.out.println("댓글목록 : "+paging.getContent());
       System.out.println("현재 페이지 : "+paging.getNumber());
       System.out.println("전체 댓글 수 :"+ paging.getTotalElements());
       System.out.println("전체 페이지 개수(소수점 올림(전체 댓글 수/한 페이지에 표시할 댓글수) ex)11/5==2.1 -> 올림하면 3 ) : "+paging.getTotalPages());
       System.out.println("boolean타입, 이전페이지가 존재하나? : "+paging.hasPrevious());
       System.out.println("boolean타입, 다음페이지가 존재하나? : "+paging.hasNext());
       System.out.println("그룹하나에 표시할 페이지 수 : "+paging.getSize());
       System.out.println("한 페이지에 표시할 댓글 수 : "+paging.getNumberOfElements());
       System.out.println("================================================================");
       
        return paging;
    }    


	// 댓글 추가
	@PostMapping("/")
	public String insert(@RequestBody Map<String, String> map) {

		Long bId = Long.parseLong((String) map.get("bId"));
		BoardEntity boardEntity = boardService.findById(bId);

		UserEntity userEntity = userService.findByUsername(map.get("writer"));

		String content = (String) map.get("content");

		String currentDate = formatDateUtil.getCurrentDate();

		ReplyEntity replyEntity = ReplyEntity.builder().writer(map.get("writer")).content(content)
				.writeDate(currentDate).user(userEntity).board(boardEntity).build();

		replyService.save(replyEntity);

		return "ok";
	}
}
