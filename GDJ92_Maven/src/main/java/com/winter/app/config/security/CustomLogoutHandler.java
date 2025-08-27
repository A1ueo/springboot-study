package com.winter.app.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//		log.info("logout handler");
//		log.info("{}", authentication);
		
		if (authentication instanceof OAuth2AccessToken) {
			MemberVO memberVO = (MemberVO) authentication.getPrincipal();
			
			if (memberVO.getSns().toUpperCase().equals("KAKAO")) {
				this.useKakao(memberVO);
			}
		}
	}
	
	private void useKakao(MemberVO memberVO) {
		
	}
}


















