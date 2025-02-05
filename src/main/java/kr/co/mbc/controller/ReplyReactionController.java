package kr.co.mbc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mbc.entity.ReplyEntity;
import kr.co.mbc.entity.ReplyReactionEntity;
import kr.co.mbc.service.ReplyReactionService;
import kr.co.mbc.service.ReplyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/replyReactions")
@RequiredArgsConstructor
public class ReplyReactionController {
	private final ReplyService replyService;
	private final ReplyReactionService replyReactionService;



	// ===============================================================================
	// 보드 좋아요, 나빠요 카운트 가져고이
	@GetMapping("/{rId}")
	public Map<String, List<ReplyReactionEntity>> getReplyReactionCount(@PathVariable("rId") Long rId) {
		Optional<ReplyEntity> replyEntity = replyService.findById(rId);

		List<ReplyReactionEntity> likes = replyReactionService.findByReplyAndReactionType(replyEntity, "like");
		List<ReplyReactionEntity> dislikes = replyReactionService.findByReplyAndReactionType(replyEntity, "dislike");

		Map<String, List<ReplyReactionEntity>> map = new HashMap<String, List<ReplyReactionEntity>>();
		map.put("likes", likes);
		map.put("dislikes", dislikes);

		return map;
	}

	// ===============================================
	// 보드 좋아요 나빠요 ajax통신 메서드
	@PostMapping("/")
	public String handleReaction(@RequestBody Map<String, Object> map) {

		Long rId = Long.parseLong((String) map.get("rId"));
		String reactionType = (String) map.get("reactionType"); // 반응 타입
		String username = (String) map.get("username"); // 사용자 이름

		ReplyReactionEntity replyReactionEntity = replyReactionService.findByReplyIdAndUsername(rId, username);

		if (replyReactionEntity == null) {
			replyReactionService.processReaction(rId, reactionType, username);
		} else {
			replyReactionEntity.setReactionType(reactionType);
			replyReactionService.save(replyReactionEntity);
		}
		
		return "ok";
	}

}
