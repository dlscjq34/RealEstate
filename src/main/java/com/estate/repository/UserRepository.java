package com.estate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.estate.model.User;


// JPA
// 기본 CRUD 기능 제공 (DAO 역할), 자동으로 빈등록, @repository 어노테이션 생략
public interface UserRepository extends JpaRepository<User, Integer>{

	// 시큐리티 로그인
	// JPA Naming 전략 : SELECT * FROM user WHERE username = ?
	Optional<User> findByUsername(String username);
	
	// 게시판에서 사용자이름으로 조회할 때 필요
	List<User> findByUsernameContaining(String search);
}
