package kr.co.mbc.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.entity.ReactionEntity;
import kr.co.mbc.service.BoardService;
import kr.co.mbc.service.ReactionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reaction")
@RequiredArgsConstructor
public class ReactionController {
	private final BoardService boardService;
	private final ReactionService reactionService;

	
	//===============================================
	//좋아요 나빠요 ajax통신 메서드
	@PostMapping("/")
	public ResponseEntity<String> handleReaction(@RequestBody Map<String, Object> requestBody) {
	
	        Long boardId =(Integer)requestBody.get("rId")*1L;   // rId 값을 String에서 Long으로 변환
	        
	        String reactionType = (String) requestBody.get("reactionType");  // 반응 타입
	        
	        String username = (String) requestBody.get("username");  // 사용자 이름
	        
	        ReactionEntity reactionEntity =reactionService.findByBoardIdAndUsername(boardId,username);

	        
	        if(reactionEntity==null) {
	        	boolean isSaved = reactionService.processReaction(boardId, reactionType, username);
	        	return ResponseEntity.ok("success");
	        }else {
	        	reactionEntity.setReactionType(reactionType);
	        	reactionService.save(reactionEntity);
	        }
	        
	        return ResponseEntity.ok("fail");
	        
	            
	  
	
	    
	}


}
