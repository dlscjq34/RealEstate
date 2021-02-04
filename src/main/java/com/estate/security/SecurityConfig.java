package com.estate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 시큐리티 설정 저장소
@Configuration	// IOC
@EnableWebSecurity	// 시큐리티 필터 등록, 시큐리티가 활성화된 상태에서 관련설정을 여기에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 인증을 시큐리티가 미리 확인하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired	// DI 
	private PrincipalDetailService principalDetailService;
	// 로그인 작업장 클래스 : 로그인 진행 후 세션저장소에 저장
	
	
	
	
	
	@Bean	// IOC	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
	
	
	
	
	@Bean	// IOC
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}	// 해시암호 생성
	

	
	
	// 시큐리티가 대신 로그인 해주려고 password를 가로챔
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// 해싱된 password로 DB의 password와 비교
		auth.userDetailsService(principalDetailService).passwordEncoder(encoder());
	}
	
	
	
	// 접속 인증 관련 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http	// 시큐리티 
			.csrf().disable()	// csrf토큰 비활성화 (테스트 시 편함)
			.authorizeRequests()	// 접속 요청이 오는데
			
				.antMatchers("/auth/**", "/js/**", "/css/**", "/images/**", "/")	// 요 경로로 오면 (매우매우 중요!!!!!!!!!!!!!!!!!!!)
				.permitAll()	// 모두 접근 허용
				
				.anyRequest()	// 허나 다른 모든 요청은
				.authenticated()	// 인증받아라
			.and()	// 따라서..
				.formLogin().loginPage("/auth/loginForm")	//  [ "/auth/**" js, css, image, / ] 외 나머지 접근은 일단 "/auth/loginForm"컨트롤러로 와
				.loginProcessingUrl("/auth/loginPro")		//  ( <- 로그인폼의 action 주소 ) 로그인 처리는 시큐리티가 한다.
				.defaultSuccessUrl("/");	// 로그인 성공 시 홈으로..
	}
}
