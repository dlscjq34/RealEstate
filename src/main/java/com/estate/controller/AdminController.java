package com.estate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estate.model.Cart;
import com.estate.model.User;
import com.estate.service.AdminService;
import com.estate.service.CartService;

//페이지 이동 컨트롤러
@Controller
public class AdminController {

	@Autowired
	AdminService adminService;
	
	
	@Autowired
	CartService cartService;
	
	
	
	//회원목록 폼
	@GetMapping("/admin/userList")
	public String userList(Model model) {
		
		
		List<User> users = adminService.userList();
		List<Cart> cart = null;
		List<String> maemoolTitle = new ArrayList<>();
		List<Integer> userCartCount = new ArrayList<>();
		
		
		for (int i = 0; i < users.size(); i++) {	//회원 수
			cart = cartService.cartForm(users.get(i).getUsername());//i번째 회원의 찜리스트
			
			if(!cart.isEmpty()) {	//i번째 회원의 찜이 있다면
				maemoolTitle.add(cart.get(0).getMaeMool().getTitle());//i번째 회원의 찜리스트의 첫번째 매물의 제목
				userCartCount.add(cart.size());	//i번째 회원의 찜리스트의 개수
			}
			else {
				maemoolTitle.add(null);//i번째 회원의 찜리스트의 첫번째 매물의 제목없으면 null
				userCartCount.add(0);	//i번째 회원의 찜리스트의 개수 없으면 0
			}
			
//			System.out.println(cart.get(i).getUsername()+", "+maemoolTitle.get(i)+", "+userCartCount.get(i));
		}
		
		System.out.println(maemoolTitle+", "+userCartCount);
		System.out.println(maemoolTitle.size());
		System.out.println(userCartCount.size());
		
		//회원정보
		
		Map<String, Object> userData = new HashMap<>();
		userData.put("users", users);
		userData.put("maemoolTitle", maemoolTitle);
		userData.put("userCartCount", userCartCount);
		
		
		model.addAttribute("userData", userData);
//		System.out.println(userData);
		
		//회원
		
		return "/admin/userList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	//회원가입 폼
//	@GetMapping("/auth/joinForm")
//	public String join() {
//		return "/user/joinForm";
//	}
//	
//	
//	
//	//로그인 폼	
//	@GetMapping("/auth/loginForm")
//	public String login() {
//		return "/user/loginForm";
//	}
//	
//	
//	
//	//로그인 폼	
//	@GetMapping("/user/detailForm")
//	public String detail() {
//		return "/user/detailForm";

}
	

