package com.winter.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityBeanConfig implements WebMvcConfigurer {

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
}
