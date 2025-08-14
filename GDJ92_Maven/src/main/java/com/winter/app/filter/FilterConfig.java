package com.winter.app.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

//	@Bean
//	FilterRegistrationBean<Filter> filterRegistrationBean() {
//		FilterRegistrationBean<Filter> fr = new FilterRegistrationBean<>();
//		
//		fr.setFilter(new TestFilter());
//		fr.addUrlPatterns("");
//		fr.setOrder(2);
//		
//		return fr;
//	}
	
	@Bean
	FilterRegistrationBean<Filter> adminFilterRegistrationBean() {
		FilterRegistrationBean<Filter> fr = new FilterRegistrationBean<>();
		
		fr.setFilter(new AdminCheckFilter());
		fr.addUrlPatterns("/notice/add","/notice/update","/notice/delete");
		fr.setOrder(1);
		
		return fr;
	}
}
