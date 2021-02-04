package com.estate.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estate.model.Cart;
import com.estate.model.MaeMool;
import com.estate.model.User;
import com.estate.service.CartService;


// 페이지 처리 컨트롤러

@RestController
public class CartProController {

	@Autowired
	CartService cartService;
	
	
	
	// 선택한 나의찜 삭제(여러개, 찜 목록에서)
	@PostMapping("/cart/cartMultiDelPro")
	public void cartMultiDelPro(@RequestBody List<String> cartIdList) {
		
		
		Iterator<String> it = cartIdList.iterator();
		if(it.next().equals("on")) {
			// mapList의 첫 요소가 "on"(cartForm의 삭제 전체선택 버튼)이면
			// 두번쩨 요소부터 while문 돌리겠다.
		}
		else{
			it = cartIdList.iterator();
			// 아니면 첫번째부터..
		}
		
		while(it.hasNext()) {
			int id = Integer.parseInt((it.next()));
			// 삭제
			cartService.cartDelPro(id);
		}
	}
	
	
	
	
	
	// 선택한 나의찜 삭제(1개, 찜 상세화면에서)
	@PostMapping("/cart/cartDelPro/{id}")
	public void cartDelPro(@PathVariable int id) {
		
		//삭제
		cartService.cartDelPro(id);
	}
		
	
	// 나의찜 등록
	@PostMapping("/cart/cartPro")
	public void cartPro(@RequestBody Map<String, Object> map) {
		
		// user객체와 maemool 객체를 cart객체에 담을 것이다.
		User user = new User();
		 
		
		MaeMool maeMool = new MaeMool();
		maeMool.setId(Integer.parseInt((String) map.get("id")));
		maeMool.setTitle((String)map.get("title"));
		maeMool.setContent((String)map.get("content"));
		maeMool.setAddr((String)map.get("addr"));
		maeMool.setFileUrl((String)map.get("fileUrl"));
		maeMool.setPrice(Integer.parseInt((String) map.get("price")));
		
		Cart cart = new Cart();
		cart.setMaeMool(maeMool);
		cart.setUsername((String)map.get("username"));
		
		cartService.cartPro(cart);
	}		
		
		
	
	
		
	
}
