package kr.co.mbc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	// 삭제 기능
	@PostMapping("/delete")
	public String delete(Long id) {
		boardService.delete(id);
		return "redirect:/board/list";
	}
	
	
	// 수정기능
	@PostMapping("/update")
	public String update(@ModelAttribute BoardEntity boardEntity) {
		boardService.update(boardEntity);
		return "redirect:/board/read/" + boardEntity.getId();
	}

	// 수정화면
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		BoardEntity boardEntity = boardService.findById(id);

		if (boardEntity == null) {
			return "redirect:/board/list";
		}

		BoardResponse boardResponse = BoardEntity.toBoardResponse(boardEntity);
		model.addAttribute("boardResponse", boardResponse);
		return "/board/update";
	}

	// 읽기 기능
	@GetMapping("/read/{id}")
	public String read(@PathVariable("id") Long id, Model model) {
		BoardEntity dto = boardService.findById(id);
		if (dto == null) {
			return "redirect:/board/list";
		}
		BoardResponse boardResponse = BoardEntity.toBoardResponse(dto);
		String con = dto.getContent();
		con = con.replace("\n", "<br>");
		dto.setContent(con);

		model.addAttribute("boardResponse", boardResponse);
		return "board/read";
	}

	// 목록 보기
	@GetMapping("/list")
	public void list(Model model) {
		List<BoardEntity> boardList = boardService.findAll();

		List<BoardResponse> boardResponseList = new ArrayList<>();
		for (BoardEntity boardEntity : boardList) {
			BoardResponse boardResponse = BoardEntity.toBoardResponse(boardEntity);
			boardResponseList.add(boardResponse);
		}

		model.addAttribute("boardResponseList", boardResponseList);
	}

	// 게시글 입력 기능
	@PostMapping("/insert")
	public String insert(BoardForm boardForm) {
		BoardEntity boardEntity = BoardEntity.toBoardEntity(boardForm);

		String naljja = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(new Date());
		boardEntity.setCreateDate(naljja);
		boardEntity.setUpdateDate(naljja);
		boardService.save(boardEntity);

		return "redirect:/board/list";
	}

	// 게시글 입력 화면
	@GetMapping("/insert")
	public void insert() {
	}



}
