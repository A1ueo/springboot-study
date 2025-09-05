package com.study.a1ueo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityBeanConfig implements WebMvcConfigurer {

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
