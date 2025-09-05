package com.study.a1ueo.security.token;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	@Autowired
	private JwtTokenManager jwtTokenManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		super(authenticationManager);
		
		this.jwtTokenManager = jwtTokenManager;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Authorization: Bearer ${ACCESS_TOKEN}
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer")) {
			header = header.split(" ")[1];
		}
		
		// token 검증
		try {
			Authentication authentication = jwtTokenManager.verifyToken(header);
			
			// 성공시 authentication을 session에 넣어주기
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			e.printStackTrace();
			// accessToken이 만료되었지만, refreshToken이 유효 하다면
			// accessToken을 새로 발급하고, 로그인 시키고 doFilter로 통과
		}
		
		chain.doFilter(request, response);
	}
}
