package com.estate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estate.model.User;
import com.estate.repository.AdminRepository;

// 서비스 로직
@Service
public class AdminService {
	
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	//회원목록 폼
	@Transactional
	public List<User> userList() {
		
		return adminRepository.findAll();
	}
	
	
	//회원삭제
	public void userDelPro(int id) {
		
		adminRepository.deleteById(id);
	}
	
	
	
	
	
//	// 회원정보 수정
//	@Transactional
//	public User detailPro(User user) {
//		
//		// update는 DB에서 select하여 User 객체를 땡겨온 후
//		// 영속성 컨텍스트에 User 객체를 올리고 update한다.
//		// 영속화된 User 객체를 변경하면 자동으로 DB에 update문 날린다.
//		User persistance = userRepository.findByUsername(user.getUsername())
//				.orElseThrow(()-> {
//					System.out.println("해당 유저는 없습니다.");
//					return null;
//				});
//		
//		
//		String encPassword = encoder.encode(user.getPassword());
//		persistance.setPassword(encPassword);
//		persistance.setAddr(user.getAddr());
//		persistance.setTel(user.getTel());
//		
//		return persistance;
//	}
//	
//	
//	
//	
//	
//	// 회원가입
//	@Transactional
//	public User joinPro(User user) {
//		
//		// 주소를 프로그램에서 정한 텍스트로 입력해야 관리자로 가입 가능
//		if(user.getPassword().equals("ONLY_FOR_ADMIN")) {
//			
//			user.setRole(Role.ADMIN);
//		}
//		else {
//			user.setRole(Role.USER);
//		}
//		
//		String encString = encoder.encode(user.getPassword());
//		user.setPassword(encString);
//		
//		userRepository.save(user);
//		
//		return user;
//	}
	
	
	
}
