package kr.co.mbc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mbc.dto.BoardForm;
import kr.co.mbc.dto.BoardResponse;
import kr.co.mbc.entity.BoardEntity;
import kr.co.mbc.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	//read
	@GetMapping("/read/{id}")
	public String read(@PathVariable("id") Long id, 
										Model model) {
		BoardEntity dto = boardService.findById(id);
		if(dto == null) {
			return "redirect:/board/list";
		}
		BoardResponse boardResponse = BoardEntity.toBoardResponse(dto);
		String con = dto.getContent();
		con = con.replace("\n", "<br>");
		dto.setContent(con);
		
		
		model.addAttribute("boardResponse", boardResponse);
		return "board/read";
	}
	
	//list
	@GetMapping("/list")
	public void list(Model model) {
		List<BoardEntity> boardList = boardService.findAll();
		
		List<BoardResponse> boardResponseList = new ArrayList<BoardResponse>();
		
		for (BoardEntity boardEntity : boardList) {
			BoardResponse boardResponse =BoardEntity.toBoardResponse(boardEntity);
			boardResponseList.add(boardResponse);
		}
		
		model.addAttribute("boardResponseList", boardResponseList);
		
	}
	
	
	//board입력기능
	@PostMapping("/insert")
	public String insert(BoardForm boardForm) {
		
		BoardEntity boardEntity =BoardEntity.toBoardEntity(boardForm);
		
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
