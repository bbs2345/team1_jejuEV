package kr.co.mbc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReplyEntity;
import kr.co.mbc.service.BoardService;
import kr.co.mbc.service.ReplyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final BoardService boardService;

    // 댓글 삭제

	
    // 댓글 수정
    
    //댓글 리스트
    @GetMapping("/{bId}")
    public List<ReplyEntity>list(@PathVariable("bId") Long bId){
    	
    	List<ReplyEntity> list=replyService.findByBoardId(bId);
    	
    	System.out.println("======================================");
    	System.out.println(bId+"ㄴㄴㅇㄴㅇㄴㅇㄴㅇㄴ");
    	System.out.println("======================================");
    	return list;
    }
    
    // 댓글 추가
	@PostMapping("/")
	public String insert(@RequestBody Map<String, String> map) {

        Long bId=Long.parseLong((String)map.get("bId"));
        BoardEntity board = boardService.findById(bId);
        
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(bId);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
		
		String writer = (String) map.get("writer");
		String content = (String) map.get("content");

		String naljja = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(new Date());

		ReplyEntity reply = new ReplyEntity(null,writer, content, naljja, naljja, board);
		replyService.save(reply);

		return "ok";
	}
}

