package com.winter.app.config.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 로그인 요청시 실행하는 필터
// username, password를 꺼내서 UserDetailService의 loadUserByUsername을 호출
//@Component
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

	// 무한 루프 가능성이 있음
	private AuthenticationManager authenticationManager;
	private JwtTokenManager jwtTokenManager;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		
		this.setFilterProcessesUrl("/member/loginProcess");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
//		System.out.println("JWT login filter ================");
//		System.out.println(username);
//		System.out.println(password);
		
		UsernamePasswordAuthenticationToken authenticationToken
			= new UsernamePasswordAuthenticationToken(username, password);
		// authenticationToken에서 UserDetailService의 loadUserByUsername을 호출하고
		// password가 일치하는지 판별하고 해당 Authentication객체를 SecurityContextHolder에 담아줌
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 사용자의 정보로 Token을 생성
		String accessToken = jwtTokenManager.makeAccessToken(authResult);
		String refreshToken = jwtTokenManager.makeRefreshToken(authResult);
		
		Cookie cookie = new Cookie("accessToken", accessToken);
		cookie.setPath("/");
		cookie.setMaxAge(180);
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
		
		cookie = new Cookie("refreshToken", refreshToken);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 10);
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println(failed.getMessage());
		
		String message = "관리자에게 문의하세요.";
//		if (exception instanceof BadCredentialsException) {
//			message = "비밀번호가 다릅니다.";
//		} else {
//			message = "아이디가 다릅니다.";
//		}
		
		switch (failed.getClass().getSimpleName()) {
		case "InternalAuthenticationServiceException":
			message = "아이디가 다릅니다.";
			break;
		case "BadCredentialsException":
			message = "비밀번호가 다릅니다.";
			break;
		case "DisabledException":
			message = "유효하지 않은 사용자입니다.";
			break;
		case "AccountExpiredException":
			message = "사용자 계정의 유효 기간이 만료되었습니다.";
			break;
		case "LockedException":
			message = "사용자 계정이 잠겨 있습니다.";
			break;
		case "CredentialsExpiredException":
			message = "자격 증명 유효 기간이 만료되었습니다.";
			break;
		case "AuthenticationCredentialsNotFoundException":
			message = "관리자에게 문의하세요.";
			break;
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("./login?failMessage=" + message);
		
//		response.sendRedirect("/member/login");
	}
}
