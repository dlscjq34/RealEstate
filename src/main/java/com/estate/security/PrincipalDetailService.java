package com.estate.security;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estate.model.User;
import com.estate.repository.UserRepository;

// 로그인 작업장 : 로그인 진행 후 세션저장소에 저장
@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; 
	
	
	
	// 로그인 시 이 메서드가 실행, username이 DB에 있는지 확인. 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()-> { 
					
					return new IllegalArgumentException("아이디나 비밀번호가 틀렸습니다."); 
				});
		
		
		// PrincipalDetail에 principal을 던져서 세션 만든다.
		return new PrincipalDetail(principal);
	}

	
}
