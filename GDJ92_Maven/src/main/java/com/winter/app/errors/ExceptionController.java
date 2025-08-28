package com.winter.app.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(exception = NullPointerException.class)
	public String error(Model model, Exception e) {
		log.error(e.getMessage());
		return "/errors/error";
	}
	
	@ExceptionHandler(exception = NumberFormatException.class)
	public String error2() {
		return "/errors/error";
	}
	
	@ExceptionHandler(exception = Exception.class)
	public String error3() {
		return "/errors/error";
	}
	
	@ExceptionHandler(exception = Throwable.class)
	public String error4() {
		return "/errors/error";
	}
}
