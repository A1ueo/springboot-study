package com.winter.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	LoginSuccessHandler loginSuccessHandler;
	@Autowired
	LoginFailHandler loginFailHandler;
	
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
					.loginPage("/member/login")
//					.usernameParameter("username") // 기본 이름은 생략 가능
//					.passwordParameter("password")
//					.usernameParameter("id")
//					.passwordParameter("pw")
//					.defaultSuccessUrl("/")		// redirect
//					.successForwardUrl("")		// foward
					.successHandler(loginSuccessHandler)	// URL과 같이 쓰면 URL이 우선순위
//					.failureUrl("/member/login")
					.failureHandler(loginFailHandler)
					;
			})
			// 개발자가 아닌 Security Filter에서 처리
			.logout((logout) -> {
				logout
					.logoutUrl("/member/logout")
//					.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
//					.logoutSuccessHandler()
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/")
					;
			})
			;
		
		return httpSecurity.build();
	}
}








