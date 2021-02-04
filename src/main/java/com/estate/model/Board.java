package com.estate.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//DB에 따른 넘버링 전략
	private int id;	// auto_increment, sequence, 무조건 자동입력
	
	@ManyToOne	// 외래키 to User table
	@JoinColumn(name="userId")
	private User user;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Column(length = 500)
	private String content;
	
	@Column(length = 100)
	private String fileUrl;
	
	
	@CreationTimestamp	// 무조건 자동 입력
	private Timestamp createDate;
	
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//DB에 따른 넘버링 전략
	private int refNo;
	
	@Column
	private int refLev;
	
	@Column
	private int refSeq;
	
	
}
