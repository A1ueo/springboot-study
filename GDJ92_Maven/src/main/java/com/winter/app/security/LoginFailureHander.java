package com.winter.app.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFailureHander implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
//		log.info("{}", exception);
		
		String message = null;
		switch (exception.getClass().getSimpleName()) {
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
		case "SessionAuthenticationException":
			message = "중복 접속 아이디입니다.";
			break;
		default:
			message = "관리자에게 문의하세요.";
			break;
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("/member/login?failMessage=" + message);
	}

	
}
