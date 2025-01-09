package kr.co.mbc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Setter;

@Component
@PropertySource(value = "classpath:fileUpload/fileUpload.properties") // src/main/resources/fileUpload/fileUpload.properties
public class UploadFileUtils {
	
	@Value("${file.upload-dir}")
	private String uploadDir; // "C:/team1_fileRepo/upload"
	
	// 파일삭제
	public void deleteFile(String fullFileName) {

			File file = new File(uploadDir, fullFileName);
			file.delete();

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	}
	
	// 다운로드
	public ResponseEntity<byte[]> download(String fullFileName) {
		
		ResponseEntity<byte[]> entity = null; // 헤더조작(변경)할 수 있고, 상탠코드 변경할 수 있고, responseBody 들어가있고~

		InputStream in = null;

		String extname = isImage(fullFileName);

		File target = null;

		
		if (extname == null) { // 이미지 파일이 아닌경우
			target = new File(uploadDir, "img.png");
			
		} else { // 이미지 파일인 경우
			target = new File(uploadDir, fullFileName);
			
		}

		
		try {
			in = new FileInputStream(target);
			HttpHeaders headers = new HttpHeaders();
			String orgname = getOrgname(fullFileName);

			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // APPLICATION_OCTET_STREAM : 모르는 미디어타입??
			String val = "attachment;filename=\"" + new String(orgname.getBytes("UTF-8"), "ISO-8859-1") + "\"";
			headers.add("Content-Disposition", val);

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return entity;
	}
	
	// base64로 인코딩
	public String convertToBase64(MultipartFile file) throws IOException {
       
		byte[] bytes = file.getBytes();
       
		String base64Encoded = Base64.getEncoder().encodeToString(bytes);
		
        return "data:image/jpeg;base64," + base64Encoded;
    }
	
	// 이미지 불러오기
	public ResponseEntity<byte[]> imgDisplay(String fullFileName) {
		
		ResponseEntity<byte[]> entity = null;

		InputStream in = null;
		
		File target = new File(uploadDir, fullFileName);

		try {
			in = new FileInputStream(target);
			HttpHeaders headers = new HttpHeaders();

			String val = "attachment;filename=\"" + new String(fullFileName.getBytes("UTF-8"), "ISO-8859-1") + "\"";
			headers.add("Content-Disposition", val);

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			
		} finally {
			
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return entity;
	}
	
	// 이미지파일 여부 확인
	public String isImage(String fileName) {
		// String[] arr = {"png", "gif", "jpg", "jpeg"};

		Map<String, String> map = new HashMap<String, String>();
		map.put("png", "png");
		map.put("gif", "gif");
		map.put("jpg", "jpg");
		map.put("jpeg", "jpeg");

		int idx = fileName.lastIndexOf(".") + 1;
		String extname = fileName.substring(idx).toLowerCase();

		String result = map.get(extname);

		return result;
	}
	
	// 파일업로드
	public String uploadFile(MultipartFile multipartFile, String serviceName, String username){
		
		String fullFileName = null;
		
		// 업로드 경로 설정
        String uploadPath = makeFolder(serviceName, username); 
        
        // 원본 파일 이름 가져오기
        String originalFilename = multipartFile.getOriginalFilename(); 

        // 파일 이름 중복 방지: UUID 사용
        String newFileName = makeNewFilename(originalFilename);  
       
		String path = uploadDir + uploadPath;
    	File dir = new File(path);
    	
    	// 디렉토리가 없다면 생성
    	if (!dir.exists()) { 
    		dir.mkdirs();
    	}
    	
        File target = new File(path, newFileName); // 파일을 저장할 대상 객체 생성

        try {
        	FileCopyUtils.copy(multipartFile.getBytes(), target); // 파일 복사
        	fullFileName = uploadPath + "/" + newFileName;
			fullFileName = fullFileName.replace(File.separatorChar, '/');
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return fullFileName;  // 업로드된 파일 이름 반환
    }
	
	// 오리지널파일네임 가져오기
	public String getOrgname(String filename) {
		
		String orgFileName = null;

		int idx = filename.indexOf("___mbc___")+ "___mbc___".length();

		orgFileName = filename.substring(idx);

		return orgFileName;
	}

	// 파일 이름 중복 방지 위해 new 파일네임 만들기
	public String makeNewFilename(String orgname) {
		
		String newFileName = null;
		
		String uid = UUID.randomUUID().toString();
		newFileName = uid + "___mbc___" + orgname; // 랜덤문자열___mbc___진짜파일네임

		return newFileName;
	}
    
	// 업로드 경로 
    private String makeFolder(String serviceName, String username) {
    	
    	// ex) uploadPath = /member/m001
    	String uploadPath = "/"+serviceName+"/"+username; 
    	
        return uploadPath;
    }

//===================================================================	
	
	// 파일업로드
	public String uploadFile(MultipartFile multipartFile, String serviceName){
		
		String fullFileName = null;
		
        String uploadPath = makeFolder(serviceName);  // "/board" 리턴받음
        
        // 원본 파일 이름 가져오기
        String originalFilename = multipartFile.getOriginalFilename(); 

        // 파일 이름 중복 방지: UUID 사용
        String newFileName = makeNewFilename(originalFilename);  
       
		String path = uploadDir+ uploadPath; // "C:/team1_fileRepo/upload" + "/board" --> C:/team1_fileRepo/upload/board
    	File dir = new File(path); // 파일객체
    	
    	// 디렉토리가 없다면 생성
    	if (!dir.exists()) { 
    		dir.mkdirs(); // 폴더가 실제로 생기는 곳
    	}
    	
        File target = new File(path, newFileName); // 파일을 저장할 대상 객체 생성

        try {
        	FileCopyUtils.copy(multipartFile.getBytes(), target); // 파일 복사
        	fullFileName = uploadPath + "/" + newFileName; // db에는 파일명이 "/board/랜덤문자___mbc___파일명"
			fullFileName = fullFileName.replace(File.separatorChar, '/');
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return fullFileName;  // 업로드된 파일 이름 반환
    }

	private String makeFolder(String serviceName) {
		
		String uploadPath = "/"+serviceName;  // /board
    	
        return uploadPath;
	}
}
