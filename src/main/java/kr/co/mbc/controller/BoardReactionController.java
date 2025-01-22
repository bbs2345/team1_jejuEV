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
import kr.co.mbc.entity.BoardReactionEntity;
import kr.co.mbc.service.BoardService;
import kr.co.mbc.service.BoardReactionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/boardReactions")
@RequiredArgsConstructor
public class BoardReactionController {
	private final BoardService boardService;
	private final BoardReactionService boardReactionService;



	// ===============================================================================
	// 보드 좋아요, 나빠요 카운트 가져고이
	@GetMapping("/{bId}")
	public Map<String, List<BoardReactionEntity>> getBoardReactionCount(@PathVariable("bId") Long bId) {
		BoardEntity boardEntity = boardService.findById(bId);

		List<BoardReactionEntity> likes = boardReactionService.findByBoardAndReactionType(boardEntity, "like");
		List<BoardReactionEntity> dislikes = boardReactionService.findByBoardAndReactionType(boardEntity, "dislike");

		Map<String, List<BoardReactionEntity>> map = new HashMap<String, List<BoardReactionEntity>>();
		map.put("likes", likes);
		map.put("dislikes", dislikes);

		return map;
	}

	// ===============================================
	// 보드 좋아요 나빠요 ajax통신 메서드
	@PostMapping("/")
	public String handleReaction(@RequestBody Map<String, Object> map) {

		Long bId = Long.parseLong((String) map.get("bId"));
		String reactionType = (String) map.get("reactionType"); // 반응 타입
		String username = (String) map.get("username"); // 사용자 이름

		BoardReactionEntity boardReactionEntity = boardReactionService.findByBoardIdAndUsername(bId, username);

		if (boardReactionEntity == null) {
			boolean isSaved = boardReactionService.processReaction(bId, reactionType, username);
		} else {
			boardReactionEntity.setReactionType(reactionType);
			boardReactionService.save(boardReactionEntity);
		}
		return "ok";
	}

}
