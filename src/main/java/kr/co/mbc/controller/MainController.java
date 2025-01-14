package kr.co.mbc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.mbc.entity.CateEntity;
import kr.co.mbc.service.CateService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	@GetMapping("/")
	public String main() {

		return "main";
	}

}
