package com.study.a1ueo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.study.a1ueo.security.token.JwtAuthenticationFilter;
import com.study.a1ueo.security.token.JwtLoginFilter;
import com.study.a1ueo.security.token.JwtTokenManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AddLogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors -> cors.configurationSource(corsConfiguration()))
			.csrf(csrf -> csrf.disable())	// Cross Site Request Forgery
			// 1. 권한 설정
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/api/notice/add").hasAnyRole("ADMIN")
					.requestMatchers("/api/notice").authenticated()
					.anyRequest().permitAll()
					)
			// 2. form 로그인 설정
			.formLogin(form -> form.disable())
			// 3. Session
			.sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					)
			// 4. Logout
			.logout(logout -> logout
					.logoutUrl("/api/member/logout")
					.invalidateHttpSession(true)
//					.deleteCookies("access_token", "refresh_token")
					.logoutSuccessHandler(logoutSuccessHandler)
					)
			// 5. Http
			.httpBasic(http -> http.disable())
			// 6. Token에 관련된 필터를 등록
			.addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			;
		
		return httpSecurity.build();
	}
	
	CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		// configuration.setAllowCredentials(true); 를 사용하면
		// setAllowedOrigins를 사용이 안됨
		// setAllowedOriginPatterns를 사용
		// "*"를 사용 못함
//		configuration.setAllowedOrigins(List.of("*"));
		configuration.setAllowedOriginPatterns(List.of("http://localhost:5173"));
		configuration.setAllowCredentials(true);
		// Method에서 *은 사용 x
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "PUT", "OPTION"));
		configuration.setAllowedHeaders(List.of("Authorization"));
		configuration.setExposedHeaders(List.of("access_token"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
}
