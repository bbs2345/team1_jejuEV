package kr.co.mbc.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.mbc.service.CsvDataService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/csv")
@RequiredArgsConstructor
public class CsvDataController {

	private final CsvDataService csvDataService;
	
	@PostMapping("/upload")
	@ResponseBody
	public ResponseEntity<String> test(MultipartHttpServletRequest mRequest) {

		MultipartFile multipartFile = mRequest.getFile("myFile");

		if (!multipartFile.isEmpty()) {
			try {
				// 파일 업로드 처리
				csvDataService.csvToDatabase(multipartFile);
				return ResponseEntity.ok("CSV 파일이 성공적으로 DB에 저장되었습니다.");
			} catch (IOException e) {
				return ResponseEntity.status(500).body("CSV 파일 처리 중 오류가 발생했습니다.");
			}
		}

		return null;

	}
	
	@GetMapping("/upload")
	public String upload() {
		
		return "/csv/upload";
	}
	
	
}
