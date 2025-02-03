package kr.co.mbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	@GetMapping("/")
	public String main() {

		return "main";
	}
	
// 메인 서브 메뉴 start	-------------------------------
	
    @GetMapping("/tourist")
    public String tourist() {
        return "tourist";
    }
    
    @GetMapping("/process")
    public String process() {
        return "process"; 
    }
    
 // 메인 서브 메뉴	end ---------------------------------

}
