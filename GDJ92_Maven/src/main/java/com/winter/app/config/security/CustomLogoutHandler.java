package com.winter.app.config.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler{

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String clientId;
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String uri;
	
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//		log.info("logout handler");
//		log.info("{}", authentication);
		
		if (authentication instanceof OAuth2AuthenticationToken) {
			MemberVO memberVO = (MemberVO) authentication.getPrincipal();
			
			if (memberVO.getSns().toUpperCase().equals("KAKAO")) {
//				String url = this.useWithKakao(memberVO);
			}
		}
	}
	
	private String useWithKakao(MemberVO memberVO) {
//		WebClient webClient = WebClient.create();
//		
//		Mono<String> result = webClient.get()
//				.uri("",
//						clientId, "http://localhost/member/logout")
//				.retrieve()
//				.bodyToMono(String.class)
//				;
//		
//		log.info("Logout2 {}", result.block());
		return "https://kauth.kakao.com/oauth/logout?client_id=" + clientId + "&logout_redirect_uri=http://localhost/member/logout";
	}
	
	private void useKakao(MemberVO memberVO) {
		Map<String, Object> param = new HashMap<>();
		param.put("target_id_type", "user_id");
		param.put("target_id", ((OAuth2User) memberVO).getName());
		
		WebClient webClient = WebClient.create();
		
		Mono<String> result = webClient.post()
				.uri("https://kapi.kakao.com/v1/user/logout")
				.header("Authorization", "Bearer " + memberVO.getAccessToken())
				.bodyValue(param)
				.retrieve()
				.bodyToMono(String.class)
				;
		
		log.info("Logout: {}", result.doOnSuccess(null).block());
	}
	
	
}


















