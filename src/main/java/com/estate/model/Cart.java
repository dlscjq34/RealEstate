package com.estate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Cart {

	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//DB에 따른 넘버링 전략
	private int id;	// auto_increment, sequence, 무조건 자동입력
	
	
//	@ManyToOne//(cascade = CascadeType.ALL)	// 외래키 to User table  옵션 : (cascade = CascadeType.ALL)
//	@JoinColumn(name="userId")
//	private User user;
	
	@Column(nullable = false, length = 30)
	private String username;
	 
	@ManyToOne
	@JoinColumn(name="maemoolId")
	private MaeMool maeMool;
	
	@ManyToOne
	@JoinColumn(name="boonyangId") 
	private BoonYang boonYang;
	
	@Column
	private int amount;
	
	
	
	
}
	
