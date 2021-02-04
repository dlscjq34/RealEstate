package com.estate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estate.model.Cart;


// JPA
// 기본 CRUD 기능 제공 (DAO 역할), 자동으로 빈등록, @repository 어노테이션 생략
public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findByUsername(String username);
	
	
	
	
	
	
	// 매물페이지의 like 검색
//	Page<MaeMool> findByTitleContaining(String search, Pageable pageable);
//	Page<MaeMool> findByAddrContaining(String search, Pageable pageable);
//	
//	int countByTitleContaining(String search);
//	int countByAddrContaining(String search);
	
	
}
