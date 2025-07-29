package com.winter.app.boards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {

	// /borad/list =>
	// /board/detail =>
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		System.out.println("boardList");
		return "/board/list";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detail() {
		System.out.println("boardDetail");
		// return이 없어도 url을 기준으로 찾아감
	}
	
}
