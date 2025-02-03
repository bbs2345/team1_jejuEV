package kr.co.mbc.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;
import kr.co.mbc.entity.CateEntity;
import kr.co.mbc.service.CateService;

@Configuration
public class CateConfig {
	
	// 어플리케이션 시작 시 카테고리 목록 초기화
	@Bean
	public CommandLineRunner init(ServletContext servletContext, CateService cateService) {
	    return new CommandLineRunner() {
	        @Override
	        public void run(String... args) throws Exception {
	            List<CateEntity> cateList = cateService.findAll();
	            
	            // 데이터가 없으면 기본 카테고리 삽입
                if (cateList.isEmpty()) {
                    CateEntity notice = new CateEntity("notice", "공지사항");
                    CateEntity free = new CateEntity("free", "자유게시판");
                    CateEntity qna = new CateEntity("qna", "QnA");
                    cateService.save(notice); 
                    cateService.save(free); 
                    cateService.save(qna); 
                    cateList = cateService.findAll();
                    System.out.println("기본 카테고리 삽입");
                }
	            
	            servletContext.setAttribute("cateList", cateList);
	            System.out.println("카테고리 리스트가 Spring Bean 설정에서 초기화되었습니다.");
	        }
	    };
	}
	

}
