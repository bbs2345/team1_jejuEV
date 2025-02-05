package kr.co.mbc.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;
import kr.co.mbc.entity.CateEntity;
import kr.co.mbc.service.CateService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CateConfig {
	
	// 어플리케이션 시작 시 카테고리 목록 초기화
	@Bean
	public CommandLineRunner init(ServletContext servletContext, CateService cateService) {
	    return new CommandLineRunner() {
	        @Override
	        public void run(String... args) throws Exception {
	        	
	        	Map<String, String> map = new HashMap<String, String>();
	        	map.put("notice", "공지사항");
	        	map.put("free", "자유게시판");
	        	map.put("qna", "QnA");
	        	
	        	Set<String> set = map.keySet();
	    		Iterator<String> it = set.iterator();
	    		
	    		while (it.hasNext()) {
	    			String key =  it.next();
	    			String value = map.get(key);
	    			cateService.checkAndCreateCate(key, value);
	    		}
	    		List<CateEntity> cateList = cateService.findAll();
	    		servletContext.setAttribute("cateList", cateList);
	            System.out.println("카테고리 리스트가 Spring Bean 설정에서 초기화되었습니다.");
	        }
	    };
	}
	

}
