package kr.co.mbc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mbc.dto.ReplyResponse;
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

		Long id = Long.parseLong((String) map.get("rId"));

		replyService.deleteById(id);
		return "ok";
	}

	// 댓글 수정
	@PutMapping("/")
	public String update(@RequestBody Map<String, String> map) {
		Long id = Long.parseLong((String) map.get("rId"));
		Optional<ReplyEntity> optionalReply = replyService.findById(id); // 댓글 존재 확인

		if (optionalReply.isPresent()) {
			ReplyEntity replyEntity = optionalReply.get();

			String content = map.get("content");
			if (content != null && !content.trim().isEmpty()) {
				replyEntity.setContent(content.trim());

				replyEntity.setWriteDate(formatDateUtil.getCurrentDate());

				replyService.save(replyEntity); // 수정된 댓글 저장
			}
		}
		return "ok";
	}

	// 댓글 리스트 (페이징 처리)
	@GetMapping("/{bId}")
	public Map<String, Object> list(@PathVariable("bId") Long bId, @RequestParam("page") int page) {

		page = page - 1;
		if (page < 0) {
			page = 0;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		// 페이징 처리된 댓글 목록 반환
		Page<ReplyEntity> paging = replyService.findByBoardIdOrderByWriteDateDesc(bId, page);
		
		List<ReplyEntity> content = paging.getContent();
		
		List<ReplyResponse> list = new ArrayList<ReplyResponse>(); 
		for (ReplyEntity replyEntity : content) {
			ReplyResponse replyResponse = ReplyEntity.toReplyResponse(replyEntity);
			list.add(replyResponse);
		}
		map.put("paging", paging);
		map.put("list", list);

		return map;
	}

	// 댓글 추가
	@PostMapping("/")
	public String insert(@RequestBody Map<String, String> map) {

		Long bId = Long.parseLong((String) map.get("bId"));
		BoardEntity boardEntity = boardService.findById(bId);

		UserEntity userEntity = userService.findByUsername(map.get("writer"));

		String content = (String) map.get("content");

		String currentDate = formatDateUtil.getCurrentDate();

		ReplyEntity replyEntity = ReplyEntity.builder().content(content)
				.writeDate(currentDate).user(userEntity).board(boardEntity).build();

		replyService.save(replyEntity);

		return "ok";
	}
}
