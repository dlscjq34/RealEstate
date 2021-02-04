package com.estate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.estate.model.Board;


// JPA
// 기본 CRUD 기능 제공 (DAO 역할), 자동으로 빈등록, @repository 어노테이션 생략
public interface BoardRepository extends JpaRepository<Board, Integer>{

	
	// 게시판 페이지의 like 검색
	Page<Board> findByTitleContaining(String search, Pageable pageable);
	Page<Board> findByUserIdContaining(String search, Pageable pageable);
	
	int countByTitleContaining(String search);
	Integer countByUserIdContaining(Integer userId);
	
	
}

