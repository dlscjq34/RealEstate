package com.estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estate.model.User;


// JPA
// 기본 CRUD 기능 제공 (DAO 역할), 자동으로 빈등록, @repository 어노테이션 생략
public interface AdminRepository extends JpaRepository<User, Integer>{

	
	
	
	
	
	
	
	
	// 매물페이지의 like 검색
//	Page<MaeMool> findByTitleContaining(String search, Pageable pageable);
//	Page<MaeMool> findByAddrContaining(String search, Pageable pageable);
//	
//	int countByTitleContaining(String search);
//	int countByAddrContaining(String search);
	
	
}
