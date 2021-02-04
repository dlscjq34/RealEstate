package com.estate.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estate.model.Board;
import com.estate.repository.BoardRepository;

// 서비스 로직
@Service
public class BoardService {

	
	@Autowired
	private BoardRepository boardRepository;
	
	
	

	// 댓글 등록
	@Transactional
	public void boardReplyPro(Board board) {
		
		boardRepository.save(board);
	}
	
	// 댓글 시퀀스 작업
	@Transactional
	public void updateRefSeq(Board board) {
	
		
		List<Board> list = boardRepository.findAll();
		int[] id = new int[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			
			id[i] = list.get(i).getId();
			Board DBboard = boardRepository.findById(id[i]).orElseThrow();
			
			
			if((DBboard.getRefNo() == board.getRefNo()) && (DBboard.getRefLev() <= board.getRefLev()) && (id[i] != board.getRefNo())) {
				System.out.println("DB id = "+id[i]);
				System.out.println("댓글 id = "+board.getId());
				DBboard.setRefSeq(DBboard.getRefSeq()+1);
				System.out.println("DBrefSeq : "+DBboard.getRefSeq());
			}
		}
	}
	
	
	
	
	
	
	
	// 메물 리스트에서 검색
	@Transactional(readOnly = true) 
	public Page<Board> boardSearch(String sel, String search, Pageable pageable) {
		
		if(search != null) {
			if(sel.equals("title")) {
				return boardRepository.findByTitleContaining(search, pageable);
			}
			else if(sel.equals("userId")) {
				
				return boardRepository.findByUserIdContaining(search, pageable);
			}
		}
		System.out.println("boardView의 잘못된 sel");
		return null;
	}
	
	// 매물 검색 갯수
	@Transactional
	public int SearchCount(String sel,  String search) {
		
		if(sel.equals("title")) {
			
			int titleCount = boardRepository.countByTitleContaining(search);
			return titleCount;
		}
		else if(sel.equals("userId")) {
			
			return boardRepository.countByUserIdContaining(2);
		}
		else {
			System.out.println("Board SearchCount의 잘못된 sel");
			return 0;
		}
	}
	
	
	// 게시글 전체 갯수
	@Transactional
	public int totalCount() {
		
		return (int)boardRepository.count();
	}
	
	
	
	
	// 게시글 삭제
	public void boardDelPro(int id) {
	
		boardRepository.deleteById(id);
	}
	
	
	
	
	// 매물 정보 수정
	@Transactional
	public void detailPro(Board board) { 
		
		// 더티체킹 
		Board persistance = findBoard(board.getId());
		persistance.setTitle(board.getTitle());
		persistance.setContent(board.getContent());
		persistance.setFileUrl(board.getFileUrl());
		
	}
	
	
	//게시글상세
	@Transactional
	public Board findBoard(int id) {
		
		return boardRepository.findById(id).orElseThrow();
	}

	
	// 게시글 전체 보기
	@Transactional(readOnly = true) 
	public Page<Board> board(Pageable pageable) {
		
		return boardRepository.findAll(pageable);
	}
	
	
	// (메인페이지)인덱스용
	@Transactional(readOnly = true) 
	public void boardforIndex() {
		 List<Board> list =  boardRepository.findAll();
		 System.out.println(list);
	}
	
	
	
	// 게시글 등록
	@Transactional
	public void boardPro(Board board) {
		
		boardRepository.save(board);
		Board persistance = boardRepository.findById(board.getId()).orElseThrow();
		persistance.setRefNo(persistance.getId());	// 더티체킹, 어거지로 주글번호 생성
		
	}
	


	

	
}
