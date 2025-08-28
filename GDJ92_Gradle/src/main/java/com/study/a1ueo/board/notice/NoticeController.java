package com.study.a1ueo.board.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

	// list
	@GetMapping("list")
	@ResponseBody
	public String list() throws Exception {
		return "list";
	}
	
	// detail
	@GetMapping("detail")
	@ResponseBody
	public String detail() throws Exception {
		return "detail";
	}
}
