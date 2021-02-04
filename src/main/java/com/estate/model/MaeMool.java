package com.estate.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
@Entity
public class MaeMool {
	
	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//DB에 따른 넘버링 전략
	private int id;	// auto_increment, sequence, 무조건 자동입력
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Column(length = 500)
	private String content;
	
	@Column(length = 150)
	private String addr;
	
	@Column(length = 200)
	private String fileUrl; 
	
	@Column
	private int price;
	
	@CreationTimestamp	// 무조건 자동 입력
	private Timestamp createDate;
	
	
}
