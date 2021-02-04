package com.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.estate.model.Board;
import com.estate.service.BoardService;


// 페이지 이동 컨트롤러
@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	
	//댓글 쓰기
	@PostMapping("/board/replyForm")
	public String replyForm(Board board, String username, Model model) {
		
		model.addAttribute("board", board);
		model.addAttribute("username", username);
		System.out.println(username);
		
		return "/board/replyForm";
	}
	
	
	
	//게시글 상세
	@GetMapping("/board/detailForm")
	public String boardDetail(Integer id, Model model) {
		
		model.addAttribute("board", boardService.findBoard(id));
		
		return "/board/detailForm";
	}
	
	
	
	
	
	//게시판 전체리스트 & 검색 겸용
	@GetMapping("/auth/board")
	public String board(Model model,
						String sel,
						String search,
						@PageableDefault(size = 5) 
							@SortDefault.SortDefaults({ 
								@SortDefault(sort = "refNo", direction = Sort.Direction.DESC),
								@SortDefault(sort = "refSeq", direction = Sort.Direction.ASC)
							}) Pageable pageable) {
						
		
		
		
		System.out.println("sel : "+sel);
		System.out.println("search : "+search);
		
		int totalCount = 0;	// 검색 여부에 따른 매물리스트 갯수
		if(search == null || search.equals("")) {
			totalCount = boardService.totalCount();
		}
		else {
			System.out.println("sel : "+sel);
			System.out.println("search : "+search);
			totalCount = boardService.SearchCount(sel, search);
		} 
		
		
		// 
		int listSize = pageable.getPageSize();
		int finalLastIndex = 
				((totalCount % listSize) > 0 )? (totalCount / listSize):(totalCount / listSize) - 1;
	
		System.out.println("totalCount : "+totalCount);
		System.out.println("finalLastIndex : "+finalLastIndex);

		
		
		
		// 검색어가 있으면 검색된 리스트 뿌린다
		if(search != null) {
			model.addAttribute("board", boardService.boardSearch(sel, search, pageable));
			model.addAttribute("sel", sel);	// 검색된 리스트에서 페이지 이동용
			model.addAttribute("search", search);// 검색된 리스트에서 페이지 이동용
		}
		else {	// 검색어가 없다면 전체 리스트 뿌린다
			model.addAttribute("board", boardService.board(pageable));
		}
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("finalLastIndex", finalLastIndex);
		
		
		return "/board/board";
	}
	
	
	//게시판 글 쓰기
	@GetMapping("/board/writeForm")
	public String write() {
		return "/board/writeForm";
	}

}
