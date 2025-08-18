package com.winter.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.interceptor.UpdateWriterCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UpdateWriterCheckInterceptor())
			.addPathPatterns("/**/update")
			.excludePathPatterns("/member/update");
	}
}
