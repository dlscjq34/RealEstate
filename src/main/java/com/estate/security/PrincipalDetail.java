package com.estate.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.estate.model.User;

import lombok.Data;

// 시큐리티 세션 저장소
@Data
public class PrincipalDetail implements UserDetails{

	private User user;
	
	
	public PrincipalDetail(User principal) {
		this.user = principal;
	}

	

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	// 계정이 만료되었나? (frue : 유효, false: 만료)
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		
		// 계정이 잠겨있나? (frue : 풀림, false: 잠김)
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		
		// 비밀번호가 만료되었나? (frue : 유효, false: 만료)
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		
		// 계정이 사용 가능한가? (frue : 유효, false: 무효)
		@Override
		public boolean isEnabled() {
			return true;
		}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
