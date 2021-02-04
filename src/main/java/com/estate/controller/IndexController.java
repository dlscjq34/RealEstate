package com.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estate.service.BoardService;
import com.estate.service.MaemoolService;


// 페이지 이동 컨트롤러
@Controller
public class IndexController {

	@Autowired
	MaemoolService maemoolService;
	
	
	@Autowired
	BoardService boardService;
	
	
	// 매물 표시
	@GetMapping("/")
	public String Index(Model model,
								@PageableDefault 
									(size = 5,
									 sort = "id",
									 direction = Sort.Direction.DESC)
								@Qualifier("maemool") Pageable pageable1,
								@PageableDefault(size = 10) 
								@SortDefault.SortDefaults({ 
									@SortDefault(sort = "refNo", direction = Sort.Direction.DESC),
									@SortDefault(sort = "refSeq", direction = Sort.Direction.ASC)})
								@Qualifier("board")	Pageable pageable2)  {
	
		
		model.addAttribute("list", maemoolService.maemoolView(pageable1));
		model.addAttribute("board", boardService.board(pageable2));
		
		return "/index";
	}
	
						
		
		
		
	
	
	
}
