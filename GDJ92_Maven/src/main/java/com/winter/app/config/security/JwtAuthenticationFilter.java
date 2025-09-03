package com.winter.app.config.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		super(authenticationManager);
		
		this.jwtTokenManager = jwtTokenManager;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Token을 검증
		
		// 1. 토큰을 꺼내기
		Cookie[] cookies = request.getCookies();
		String token = "";
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("accessToken")) {
					token = c.getValue();
					break;
				}
			}
//			System.out.println("Token: " + token);
			// 2. 토큰을 검증
			if (token != null && token.length() != 0) {
				try {
					Authentication authentication = jwtTokenManager.getAuthenticationByToken(token);
					SecurityContextHolder.getContext().setAuthentication(authentication);
//					System.out.println(authentication.getName());
				} catch (Exception e) {
					e.printStackTrace();
					// SecurityException || MalformedException || SignatureException : 유효하지 않은 JWT 서명
					// ExpiredJwtException : 기간이 만료된 토큰
					// UnSupportedJwtException : 지원되지 않는 토큰
					// IllegalArgumentException : 잘못된 토큰
					
					if (e instanceof ExpiredJwtException) {
						for (Cookie c : cookies) {
							if (c.getName().equals("refreshToken")) {
								String refreshToken = c.getValue();
								try {
									Authentication authentication
										= jwtTokenManager.getAuthenticationByToken(refreshToken);
									SecurityContextHolder.getContext().setAuthentication(authentication);
									String accessToken = jwtTokenManager.makeAccessToken(authentication);
									
									Cookie cookie = new Cookie("accessToken", accessToken);
									cookie.setPath("/");
									cookie.setMaxAge(180);
									cookie.setHttpOnly(true);
									
									response.addCookie(cookie);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								break;
							}
						}
					}
				}
			}
		}
		
		chain.doFilter(request, response);
	}
}
