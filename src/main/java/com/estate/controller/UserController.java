package com.estate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//페이지 이동 컨트롤러
@Controller
public class UserController {

	
	//회원가입 폼
	@GetMapping("/auth/joinForm")
	public String join() {
		return "/user/joinForm";
	}
	
	
	
	//로그인 폼	
	@GetMapping("/auth/loginForm")
	public String login() {
		return "/user/loginForm";
	}
	
	
	
	//로그인 폼	
	@GetMapping("/user/detailForm")
	public String detail() {
		return "/user/detailForm";
	}
}
