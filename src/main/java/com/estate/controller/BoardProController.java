package com.estate.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estate.model.Board;
import com.estate.model.User;
import com.estate.service.BoardService;


// 페이지 처리 컨트롤러

@Controller
public class BoardProController {

	@Autowired
	BoardService boardService;
	
	// 댓글 등록 처리
	@PostMapping("/board/boardReplyPro")	// map : 파일 외 나머지 데이터들
	public String boardReplyPro(@RequestParam Map<String, String> map, 
						     MultipartFile fileUrl) {
						     
		System.out.println((map.get("userId")));
		
		
		User user = new User();//게시판으로 갈 데이터(userId)
		Board board= new Board();//게시판으로 갈 데이터
				
		
		//첨부파일이 있을때만 파일 처리 작업
		if(!fileUrl.getOriginalFilename().equals("")) {
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
			
			String DBpath = pathAddr.substring(pathAddr.length() - 8) + fileName;//DB에는 주소를 /images/부터 저장
			board.setFileUrl(DBpath);//게시판으로 갈 데이터
		}
		
		
		
		user.setId(Integer.parseInt((map.get("userId"))));
		board.setUser(user);
		board.setTitle(map.get("title"));
		board.setContent(map.get("content"));
		
		board.setRefNo(Integer.parseInt(map.get("refNo")));//주글번호
		board.setRefLev(Integer.parseInt(map.get("refLev"))+1);
		board.setRefSeq(Integer.parseInt(map.get("refSeq"))+1);
		
		
		
		//DB에 Insert
		boardService.boardReplyPro(board);
		//DB 시퀀스 업데이트
		boardService.updateRefSeq(board);
		
		return "redirect:/auth/board";
	}
	
	
	
	
	
	

	
	// 선택한 게시글 삭제(여러개, 게시판목록에서)
	@ResponseBody
	@PostMapping("/board/boardMultiDelPro")
	public void boardMultiDelPro(@RequestBody List<String> boardIdList) {
		
		System.out.println("***********"+boardIdList);
		
		Iterator<String> it = boardIdList.iterator();
		if(it.next().equals("on")) {
			// mapList의 첫 요소가 "on"(cartForm의 삭제 전체선택 버튼)이면
			// 두번쩨 요소부터 while문 돌리겠다.
		}
		else{
			it = boardIdList.iterator();
			// 아니면 첫번째부터..
		}
		
		while(it.hasNext()) {
			int id = Integer.parseInt((it.next()));
			System.out.println("id : "+id);
			// 삭제
			boardService.boardDelPro(id);
		}
	}
		
		
	
	// 게시글 삭제 처리
	@ResponseBody
	@PostMapping("/board/boardDelPro")
	public void boardDelPro(@RequestBody int boardId) {

		//삭제
		boardService.boardDelPro(boardId);
	}
	
	
	
	
	
	
	// 게시글 수정 처리 
	@PostMapping("/board/boardDetailPro")	// map : 파일 외 나머지 데이터들
	public String boardDetailPro(@RequestParam Map<String, String> map, 
						     MultipartFile newfileUrl, HttpServletResponse resp) throws Exception {
					
		
		
		//기존 파일
		String oldFileUrl = (String)map.get("oldFileUrl");
		//기존 파일 삭제 여부('true', 'false')
		String DelOldFile = (String)map.get("DelOldFile");
		
		
		 
		// 파일 중복 대비 고유파일명 생성, 저장경로 설정
		String fileName = null;	// UUID + 파일명
		String pathAddr = null;	// 물리적 파일 저장 경로
		String pathName = null; // 최종 파일 경로(물리적 파일 저장 경로 + UUID파일명)
		String DBpath = null;	// DB에 저장될 경로(jsp인식 주소 기준 : /image/로 시작)
		
		
		//DB에 Update할 준비
		Board board = new Board();
		
				
		// 새로 첨부한 파일이 있다면
		if(!(newfileUrl.getOriginalFilename().equals(""))) {	
			System.out.println("새파일 첨부됨");
			
				
			fileName = UUID.randomUUID().toString()+"-"+newfileUrl.getOriginalFilename();
			pathAddr = "C:\\JSP\\JAVA\\SpringRealEstate\\RealEstateServer\\src\\main\\resources\\static\\images\\";
			pathName = pathAddr+fileName;
			DBpath = pathAddr.substring(pathAddr.length() - 8) + fileName;//DB에는 주소를 /images/부터 저장

			// 설정한 경로에 파일 저장
			File dest = new File(pathName);
			try {
				newfileUrl.transferTo(dest);
			} 
			catch (Exception e) { 
				e.printStackTrace();
			}
			
			// 기존 파일 있으면 삭제 (물리경로로 설정해줘야 실제로 삭제 가능)
			File delFile = new File("C:\\JSP\\JAVA\\SpringRealEstate\\RealEstateServer\\src\\main\\resources\\static"+oldFileUrl);
			if(delFile.exists()) {
				System.out.println("삭제 작업");
				delFile.delete();
			}
			
			//변경된 파일경로 update
			board.setFileUrl(DBpath);
		}
		// 기존 파일 있으면 삭제 (물리경로로 설정해줘야 실제로 삭제 가능)
		else if(DelOldFile != null && DelOldFile.equals("true")) {
			
			
			File delFile = new File("C:\\JSP\\JAVA\\SpringRealEstate\\RealEstateServer\\src\\main\\resources\\static"+oldFileUrl);
			if(delFile.exists()) {
				System.out.println("삭제 작업");
				delFile.delete();
			}
		}
		// 새로첨부한 파일도 없고 기존파일도 삭제하지 않는다면 기존파일이 DB로..
		else { 
			
			board.setFileUrl(oldFileUrl);
		}
	
		
		
		board.setTitle(map.get("title"));
		board.setContent(map.get("content"));
		board.setId(Integer.parseInt(map.get("boardId")));
	
		boardService.detailPro(board);
		
		
		return "redirect:/auth/board";
	}
	
	
	
	
	
	
	
	
	// 글쓰기 등록 처리
	@PostMapping("/board/writePro")	// map : 파일 외 나머지 데이터들
	public String writePro(@RequestParam Map<String, String> map, 
						     MultipartFile fileUrl) {
						     
		
		User user = new User();//게시판으로 갈 데이터(userId)
		user.setId(Integer.parseInt((map.get("userId"))));
		
		Board board= new Board();//게시판으로 갈 데이터
		board.setUser(user);
		board.setTitle(map.get("title"));
		board.setContent(map.get("content"));
		
				
		
		//첨부파일이 있을때만 파일 처리 작업
		if(!fileUrl.getOriginalFilename().equals("")) {
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
			
			String DBpath = pathAddr.substring(pathAddr.length() - 8) + fileName;//DB에는 주소를 /images/부터 저장
			board.setFileUrl(DBpath);//게시판으로 갈 데이터
		}
		
		
		
		//DB에 Insert
		boardService.boardPro(board);
		
		return "redirect:/auth/board";
	}
		
		
	
	
		
	
}
