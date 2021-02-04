package com.estate.controller;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estate.model.MaeMool;
import com.estate.service.MaemoolService;


// 데이터 처리 컨트롤러
@Controller
public class MamoolProController {
	
	@Autowired
	MaemoolService maemoolService;
	
	 
	
	// 매물 삭제 처리
	@ResponseBody
	@PostMapping("/maemool/maemoolDelPro/{id}")	// map : 파일 외 나머지 데이터들
	public void maemoolDelPro(@PathVariable int id) {
		
		//삭제
		maemoolService.maemoolDelPro(id);
		
	}
	
	
	
	
	
	// 매물 수정 처리 
	@PostMapping("/maemool/maemoolDetailPro")	// map : 파일 외 나머지 데이터들
	public String maemoolDetailPro(@RequestParam Map<String, Object> map, 
						     MultipartFile fileUrl) {
						     
		
		
		//기존 파일, 신규 파일
		String oldFileUrl = (String)map.get("oldFileUrl"); 
		String newFileUrl = fileUrl.getOriginalFilename();
		
		
		// 파일 중복 대비 고유파일명 생성, 저장경로 설정
		String fileName = null;	// UUID + 파일명
		String pathAddr = null;	// 물리적 파일 저장 경로
		String pathName = null; // 최종 파일 경로(물리적 파일 저장 경로 + UUID파일명)
		String DBpath = oldFileUrl;	// DB에 저장될 경로(jsp인식 주소 기준 : /image/로 시작)
		
		
		if(!newFileUrl.equals("")) {	// 새로 업로드한 파일이 있으면
			
			fileName = UUID.randomUUID().toString()+"-"+fileUrl.getOriginalFilename();
			pathAddr = "C:\\JSP\\JAVA\\SpringRealEstate\\RealEstateServer\\src\\main\\resources\\static\\images\\";
			pathName = pathAddr+fileName;
			DBpath = pathAddr.substring(pathAddr.length() - 8) + fileName;//DB에는 주소를 /images/부터 저장

			// 설정한 경로에 파일 저장
			File dest = new File(pathName);
			try {
				fileUrl.transferTo(dest);
			} 
			catch (Exception e) { 
				e.printStackTrace();
			}
			
			// 기존 파일 있으면 삭제 (물리경로로 설정해줘야 실제로 삭제 가능)
			File delFile = new File("C:\\JSP\\JAVA\\SpringRealEstate\\RealEstateServer\\src\\main\\resources\\static"+oldFileUrl);
			if(delFile.exists()) {
				delFile.delete();
			}
		}
		
		
		//DB에 Update할 준비
		MaeMool maeMool = new MaeMool();
		maeMool.setId(Integer.parseInt((String)map.get("id")));
		maeMool.setTitle((String)map.get("title"));
		maeMool.setContent((String)map.get("content"));
		maeMool.setPrice(Integer.parseInt((String)map.get("price")));
		maeMool.setFileUrl(DBpath);
		
		// 업데이트
		maemoolService.detailPro(maeMool);
		
		return "redirect:/auth/maemoolView";
	}
		
		
		
		
		
		
		
		
		
	

	// 매물 등록 처리
	@PostMapping("/maemool/maemoolPro")	// map : 파일 외 나머지 데이터들
	public String maemoolPro(@RequestParam Map<String, Object> map, 
						     MultipartFile fileUrl) {
						     
		
		// 파일 중복 대비 고유파일명 생성, 저장경로 설정
		
		String fileName = UUID.randomUUID().toString()+"-"+fileUrl.getOriginalFilename();
		String pathAddr = "C:\\JSP\\JAVA\\SpringRealEstate\\RealEstateServer\\src\\main\\resources\\static\\images\\";
		String pathName = pathAddr+fileName;
		
		
		// 설정한 경로에 파일 저장
		File dest = new File(pathName);
		try {
			fileUrl.transferTo(dest);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//DB에 Insert할 준비
		MaeMool maeMool = new MaeMool();
		String DBpath = pathAddr.substring(pathAddr.length() - 8) + fileName;//DB에는 주소를 /images/부터 저장
		maeMool.setTitle((String)map.get("title"));
		maeMool.setContent((String)map.get("content"));
		maeMool.setAddr((String)map.get("addr"));
		maeMool.setPrice(Integer.parseInt((String)map.get("price")));
		maeMool.setFileUrl(DBpath);
		
		
		
		maemoolService.maemoolPro(maeMool);
		
		return "redirect:/auth/maemoolView";
	}
	
	
}
