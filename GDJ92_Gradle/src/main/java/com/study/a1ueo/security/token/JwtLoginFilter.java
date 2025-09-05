package com.study.a1ueo.security.token;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JwtTokenManager jwtTokenManager;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		
		this.setFilterProcessesUrl("/api/member/login");
	}
	
	// 로그인 처리
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authentication
			= new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authentication);
	}
	
	// 로그인이 성공 했을 때
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String accessToken = jwtTokenManager.createAccessToken(authResult);
		String refreshToken = jwtTokenManager.createRefreshToken(authResult);
		
		/*
		 * 개발시 포트번호가 드라기 때문에 쿠키가 저장되지 않는다.
		 * Boot에 같이 빌드해서 배포하면 쿠키가 저장됨
		 */
		
		response.setHeader("access_token", accessToken);
	}
	
	// 로그인이 실패 했을 때
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(failed.getMessage());
	}
	
}
