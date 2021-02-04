package com.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estate.service.MaemoolService;


// 페이지 이동 컨트롤러
@Controller
public class MamoolController {

	@Autowired
	MaemoolService maemoolService;
	
	
	
	//매물 상세
	@GetMapping("/maemool/detailForm")
	public String detailForm(Integer id, Model model) {
		
		model.addAttribute("maemool", maemoolService.findMamool(id));
		
		return "/maemool/detailForm";
	}
		
	
	
	
	// 매물 전체리스트 & 검색 겸용
	@GetMapping("/auth/maemoolView")
	public String maemoolSearch(Model model,
								String sel,
								String search,
								@PageableDefault 
									(size = 4,
									 sort = "id",
									 direction = Sort.Direction.DESC) Pageable pageable)  {

		
		
		System.out.println("sel : "+sel);
		System.out.println("search : "+search);
		
		
		//매물 메인 페이지에 검색된 데이터 뿌릴 준비
		String pathAddr = "C:\\JSP\\JAVA\\SpringRealEstate\\RealEstateServer\\src\\main\\resources\\static\\images\\";
		System.out.println(pathAddr.substring(pathAddr.length() - 8)); 
		
		int totalCount = 0;	// 검색 여부에 따른 매물리스트 갯수
		if(search == null) {
			totalCount = maemoolService.totalCount();
		}
		else {
			totalCount = maemoolService.SearchCount(sel, search);
		}
		
		
		int finalLastIndex = 
				((totalCount % 4) > 0 )? (totalCount / 4):(totalCount / 4) - 1;
	
		System.out.println("totalCount : "+totalCount);
		System.out.println("finalLastIndex : "+finalLastIndex);

		// 검색어가 있으면 검색된 리스트 뿌린다
		if(search != null) {
			model.addAttribute("list", maemoolService.maemoolViewSearch(sel, search, pageable));
			model.addAttribute("sel", sel);	// 검색된 리스트에서 페이지 이동용
			model.addAttribute("search", search);// 검색된 리스트에서 페이지 이동용
		}
		else {	// 검색어가 없다면 전체 리스트 뿌린다
			model.addAttribute("list", maemoolService.maemoolView(pageable));
		}
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("finalLastIndex", finalLastIndex);
		
		return "/maemool/maemoolView";
	}
		
	
	
	
	
	// 매물 등록폼
	@GetMapping("/maemool/maemoolForm")
	public String maemoolForm() {
		return "/maemool/maemoolForm";
	}
}
