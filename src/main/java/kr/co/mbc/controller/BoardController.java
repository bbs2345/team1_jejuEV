package kr.co.mbc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mbc.dto.BoardForm;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	
	//board입력기능
	@PostMapping("/insert")
	public String insert(BoardForm BoardForm) {
		
		BoardEntity boardEntity =BoardEntity.toBoardEntity(BoardForm);
		
		String naljja = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(new Date());
		boardEntity.setCreateDate(naljja);
		boardEntity.setUpdateDate(naljja);
		boardService.save(boardEntity);
		
		return "/board/list";
	}
	
	//board입력
	@GetMapping("/insert")
	public void insert() {
		
	}
}
