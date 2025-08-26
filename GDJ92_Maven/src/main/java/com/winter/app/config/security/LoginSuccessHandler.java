package com.winter.app.config.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	// 로그인이 성공 했을 때 실행
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("로그인 성공");
		String rememberId = request.getParameter("rememberId");
		log.info("{}", rememberId);
		
		String username = request.getParameter("username");
		
		Cookie cookie = new Cookie("remember_id", authentication.getName());
		if (rememberId != null && rememberId.equals("1")) {
			cookie.setMaxAge(60 * 60 * 24 * 30);
			cookie.setPath("/");
		} else {
			cookie.setMaxAge(0);
			cookie.setPath("/");	// 삭제할 때도 path를 맞춰야됨
		}
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}
}
