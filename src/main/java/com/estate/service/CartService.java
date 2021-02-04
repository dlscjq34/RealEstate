package com.estate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estate.model.Cart;
import com.estate.repository.CartRepository;

// 서비스 로직
@Service
public class CartService {
	
	
	@Autowired
	private CartRepository cartRepository;
	
	
	// 나의찜 삭제
	public void cartDelPro(int id) {
		
		cartRepository.deleteById(id);
	}



	// 나의찜 등록
	@Transactional
	public void cartPro(Cart cart) {
		
		cartRepository.save(cart);
	}


	// 나의찜 보기 
	public List<Cart> cartForm(String username) {
		
		return cartRepository.findByUsername(username);
	}

	
}
