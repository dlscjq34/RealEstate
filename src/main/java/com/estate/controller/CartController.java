package com.estate.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estate.model.Cart;
import com.estate.service.CartService;
import com.estate.service.MaemoolService;


// 페이지 이동 컨트롤러
@Controller
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	MaemoolService maemoolService; 
	
	
	
	
	//나의찜 메인 화면
	@GetMapping("/cart/cartForm")
	public String cartForm(Model model, Principal principal) {
		
		if(principal != null) {
			List<Cart> cart = cartService.cartForm(principal.getName());
			
			model.addAttribute("cart", cart);
		}
		
		
		return "/cart/cartForm";
	}
		
	//나의찜 상세화면
	@GetMapping("cart/detailForm")
	public String detailForm(Integer maemoolId, int cartId, Model model) {
		
		model.addAttribute("maemool", maemoolService.findMamool(maemoolId));
		model.addAttribute("cartId", cartId);
		
		return "/cart/detailForm";
	}
}
