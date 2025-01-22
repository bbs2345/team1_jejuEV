package kr.co.mbc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/reactions")
@RequiredArgsConstructor
public class ReactionController {
	private final BoardService boardService;
	private final ReactionService reactionService;
	
	// 좋아요, 나빠요 카운트 가져고이
	@GetMapping("/{bId}")
	public Map<String, List<ReactionEntity>> getReactionCount(@PathVariable("bId") Long bId) {
		BoardEntity boardEntity = boardService.findById(bId);
		
		List<ReactionEntity> likes = reactionService.findByBoardAndReactionType(boardEntity, "like");
		List<ReactionEntity> dislikes = reactionService.findByBoardAndReactionType(boardEntity, "dislike");
		
		
		Map<String, List<ReactionEntity>> map = new HashMap<String, List<ReactionEntity>>();
		map.put("likes", likes);
		map.put("dislikes", dislikes);
		
		return map;
	}
	
	//===============================================
	//좋아요 나빠요 ajax통신 메서드
	@PostMapping("/")
	public String handleReaction(@RequestBody Map<String, Object> map) {
	
	        Long bId = Long.parseLong((String)map.get("bId"));
	        String reactionType = (String) map.get("reactionType");  // 반응 타입
	        String username = (String) map.get("username");  // 사용자 이름
	        
	        ReactionEntity reactionEntity =reactionService.findByBoardIdAndUsername(bId,username);
	        
	        if(reactionEntity==null) {
	        	boolean isSaved = reactionService.processReaction(bId, reactionType, username);
	        }else {
	        	reactionEntity.setReactionType(reactionType);
	        	reactionService.save(reactionEntity);
	        }
	        return "ok";
	}


}
