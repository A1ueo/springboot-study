package com.winter.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import com.winter.app.member.MemberService;

@Configuration
@EnableWebSecurity	// (debug = true)
public class SecurityConfig {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired
	private LoginFailHandler loginFailHandler;
	@Autowired
	private CustomLogoutHandler logoutHandler;
	@Autowired
	private CustomLogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	MemberService memberService;
	@Autowired
	private JwtTokenManager jwtTokenManager;
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	HttpFirewall defaultFirewall() {
		return new DefaultHttpFirewall();
	}

	// 정적 자원들을 Security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		// web => WebSecurity
		return web -> {
			web.httpFirewall(defaultFirewall());
			web.ignoring().requestMatchers("/css/**", "/js/**", "/vendor/**")
				.requestMatchers("/file/**");
		};
	}
	
	// 인증과 권한의 설정
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable())
			
			// 권한에 관련된 설정
			.authorizeHttpRequests(auth -> {
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete")
					.hasRole("ADMIN") // ROLE_ 는 자동으로 제거됨
					
					.requestMatchers("/product/add", "/product/update", "/product/delete")
					.hasAnyRole("MANAGER", "ADMIN")
					
					.requestMatchers("/member/detail", "/member/update", "/memeber/delete")
//					.access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER')")
					.authenticated()
					
					.requestMatchers("/member/login").permitAll()
					.anyRequest().permitAll() // 다른 모든 요청은 허용
					;
			})
			// form 관련 설정
			// 개발자가 로그인 검증을 하지 않는다, Security Filter에서 검증
			.formLogin(form -> {
				form
					.disable()
					;
			})
			.logout(logout -> logout
					.logoutUrl("/member/logout")
					.invalidateHttpSession(true)
					.deleteCookies("accessToken", "refreshToken")
					.logoutSuccessUrl("/")
			)
			// Session 인증 방식이 아닌
			// Token 인증방식이기 때문에 Session을 사용하지 않음
			.sessionManagement((manage) -> {
				manage
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					;
			})
			.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			.addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
//			.oauth2Login(o -> o
//				.userInfoEndpoint(user -> user
//					.userService(memberService)
//				)
//			)
//			.httpBasic(httpBasic -> httpBasic
//				.disable()
//			)
			;
		
		return httpSecurity.build();
	}
}








