package com.winter.app.security;

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
	LoginFailureHander loginFailureHander;
	
	@Bean
	HttpFirewall defaultFireWall() {
		return new DefaultHttpFirewall();
	}
	
	@Bean
	WebSecurityCustomizer customizer() {
		return web -> {
			web.httpFirewall(defaultFireWall());
		};
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> {
				auth
					.requestMatchers("/member/detail", "/member/update", "/member/delete")
					.authenticated()
					.requestMatchers("/product/add", "/product/update", "/product/delete")
					.hasAnyRole("MANAGER", "ADMIN")
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete")
					.hasRole("ADMIN")
					.anyRequest().permitAll()
					;
			})
			.formLogin(form -> {
				form
					.loginPage("/member/login")
					.defaultSuccessUrl("/")
//					.failureUrl("/member/login")
					.failureHandler(loginFailureHander)
					;
			})
			.logout((logout) -> {
				logout
					.logoutUrl("/member/logout")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/")
					;
			})
			.sessionManagement(session -> session
					.invalidSessionUrl("/member/login")
					.maximumSessions(1)
					.maxSessionsPreventsLogin(true)
					.expiredUrl("/")
			)
			;
		return httpSecurity.build();
	}
}
