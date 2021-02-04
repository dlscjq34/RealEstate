package com.estate.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class User {

	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//DB에 따른 넘버링 전략
	private int id;	// auto_increment, sequence, 무조건 자동입력
	
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;	//아이디
	
	
	@Column(nullable = false, length = 100)
	private String password;	// 시큐리티로 해쉬로 비밀번호 암호화
	
	
	@Column(length = 100)
	private String addr;	// 시큐리티로 해쉬로 비밀번호 암호화
	
	
	@Column(length = 15)
	private String tel;	// 시큐리티로 해쉬로 비밀번호 암호화
	
	
	@Enumerated(EnumType.STRING)	// DB에는 Role type이 없으므로 문자열이라고 알려줘야함.
	private Role role;	// 기본값 입력하고 싶은데 오타 확률이 있으므로 enum 사용
	
	
	@CreationTimestamp	// 무조건 자동 입력
	private Timestamp createDate;


	
	
	
	
//	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)	// mappedBy : 연관관계의 주인이 아니다(난 FK가 아니다) DB에 컬럼 만들지 마라.
//	private Set<Board> board;		// 		  			   FK는 따로 있다. board 테이블에.
	
	//cascade = CascadeType.ALL
//	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)	// mappedBy : 연관관계의 주인이 아니다(난 FK가 아니다) DB에 컬럼 만들지 마라.
//	private Set<Cart> cart;// = new HashSet<>();		// 		  			   FK는 따로 있다. cart 테이블에.
	
}

