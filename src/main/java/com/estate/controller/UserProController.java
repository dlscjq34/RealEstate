package com.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estate.model.User;
import com.estate.service.UserService;

// 데이터 처리 컨트롤러
@RestController
public class UserProController {
	
	@Autowired	// DI
	private UserService userService;

	
	@Autowired	// DI 
	private AuthenticationManager authenticationManager; 
	
	
	
	
	// 아이디 중복체크
	@PostMapping("/auth/idCheck")
	public boolean idCheck(@RequestBody String username) {
		
		return userService.idCheck(username);
	}
	
	
	// 회원가입 처리
	@PostMapping("/auth/joinPro")
	public User joinPro(@RequestBody User user) {
		
		userService.joinPro(user);
		
		return user;
	}
	
	
	
	
	// 회원정보 수정 처리
	@PostMapping("/user/detailPro")
	public User detailPro(@RequestBody User user) {
		
		// 더티체킹으로 업데이트된 영속화-유저객체
		User persistance = userService.detailPro(user);
		System.out.println("user.getPassword() : "+user.getPassword());
		
		// 이 시점.. 트랜잭션은 종료(DB값 변경 완료), 세션은 아직 그대로
		// 세션에 변경내용 적용할 것
		Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(),
																user.getPassword()));
		// 컨텍스트홀더는 컨텍스트를 준비하고 컨텍스트에 인증을 집어 넣는다.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return persistance; 
	}
	
	
	
	
}
