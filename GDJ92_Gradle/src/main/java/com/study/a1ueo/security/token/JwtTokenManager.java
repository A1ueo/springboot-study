package com.study.a1ueo.security.token;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.study.a1ueo.member.MemberRepository;
import com.study.a1ueo.member.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {

	// 1. 토큰 유효 시간
	@Value("${jwt.valid.time.access}")
	private Long accessValidTime;
	// 2. 리프레시 토큰 유효 시간
	@Value("${jwt.valid.time.refresh}")
	private Long refreshValidTime;
	// 3. 발급자
	@Value("${jwt.issuer}")
	private String issuer;
	// 4. Secret
	@Value("${jwt.secret}")
	private String secret;
	
	// 5. Key
	private SecretKey key;
	
	@Autowired
	private MemberRepository memberRepository;
	
	// A. key 생성하는 메서드
	@PostConstruct
	public void init() {
		byte[] base64 = Base64.getEncoder().encode(secret.getBytes());
		key = Keys.hmacShaKeyFor(base64);
	}
	
	// B. tokken 생성하는 메서드
	private String createToken(Authentication authentication, Long validTime) {
		return Jwts
				.builder()
				// 사용자의 ID
				.subject(authentication.getName())
				// claim -> 추가 정보
				.claim("roles", authentication.getAuthorities())
				// 토큰 생성 시간
				.issuedAt(new Date())
				// 유효 시간
				.expiration(new Date(System.currentTimeMillis() + validTime))
				// 발급자
				.issuer(issuer)
				// 키
				.signWith(key)
				.compact()
				;
	}
	
	// C. Access Token 생성
	public String createAccessToken(Authentication authentication) {
		return createToken(authentication, accessValidTime);
	}
	
	// D. Refresh Token 생성
	public String createRefreshToken(Authentication authentication) {
		return createToken(authentication, refreshValidTime);
	}
	
	// E. Token 검증
	public Authentication verifyToken(String token) throws Exception {
		// 검증에 실패하면 Exception 발생
		Claims claims = Jwts
				.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				;
		
		Optional<MemberVO> result = memberRepository.findById(claims.getSubject());
		
		MemberVO memberVO = result.get();
		
		return new UsernamePasswordAuthenticationToken(memberVO, null, memberVO.getAuthorities());
	}
}
