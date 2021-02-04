package com.estate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.estate.model.MaeMool;
import com.estate.repository.MaemoolRepository;

// 서비스 로직
@Service
public class MaemoolService {
	
	
	@Autowired
	private MaemoolRepository maemoolRepository;
	
	
	//매물 삭제
	@Transactional
	public void maemoolDelPro(int id) {
	
		maemoolRepository.deleteById(id);
	}
	
	

	// 매물 정보 수정
	@Transactional
	public void detailPro(MaeMool maeMool) { 

		// 더티체킹 
		MaeMool persistance = findMamool(maeMool.getId());
		persistance.setTitle(maeMool.getTitle());
		persistance.setContent(maeMool.getContent());
		persistance.setFileUrl(maeMool.getFileUrl());
		persistance.setPrice(maeMool.getPrice());
	}
	
	
	
	// 매물 상세 검색
		@Transactional
		public MaeMool findMamool(int id) {
			
			return maemoolRepository.findById(id).orElseThrow();
		}
		
		
		
	
	// 메물 리스트에서 검색
	@Transactional(readOnly = true) 
	public Page<MaeMool> maemoolViewSearch(String sel, String search, Pageable pageable) {
		
		if(sel.equals("title")) {
			return maemoolRepository.findByTitleContaining(search, pageable);
		}
		else if(sel.equals("addr")) {
			return maemoolRepository.findByAddrContaining(search, pageable);
		}
		else {
			System.out.println("maemoolView의 잘못된 sel");
			return null;
		}

	}
	

	// 매물 전체 보기
	@Transactional(readOnly = true) 
	public Page<MaeMool> maemoolView(Pageable pageable) {
		
		return maemoolRepository.findAll(pageable);
	}
	
	
	
	
	// 매물 전체 갯수
	@Transactional
	public int totalCount() {
		
		return (int)maemoolRepository.count();
	}
	
	
	
	
	// 매물 검색 갯수
	@Transactional
	public int SearchCount(String sel,  String search) {
		
		if(sel.equals("title")) {
			int titleCount = maemoolRepository.countByTitleContaining(search);
			System.out.println("titleCount : "+titleCount);
			return titleCount;
		}
		else if(sel.equals("addr")) {
			int addrCount = maemoolRepository.countByAddrContaining(search);
			System.out.println("addrCount : "+addrCount);
			return addrCount;
		}
		else {
			System.out.println("maemoolView의 잘못된 sel");
			return 0;
		}
	}
	
	
	
	
	// 매물정보 등록
	@Transactional
	public void maemoolPro(MaeMool maeMool) {
		
		maemoolRepository.save(maeMool);
	}


	
		
		
	
	
}
