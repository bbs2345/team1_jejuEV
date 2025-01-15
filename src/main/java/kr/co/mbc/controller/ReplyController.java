package kr.co.mbc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	// 댓글 리스트
	@GetMapping("/{bId}")
	public List<ReplyEntity> list(@PathVariable("bId") Long bId) {

		List<ReplyEntity> list = replyService.findByBoardId(bId);

		return list;
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
				.createDate(currentDate).user(userEntity).board(boardEntity).build();

		replyService.save(replyEntity);

		return "ok";
	}
}
