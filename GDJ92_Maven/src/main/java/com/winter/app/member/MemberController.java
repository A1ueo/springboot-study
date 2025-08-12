package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Value("${board.profile}")
	private String name;
	
	@ModelAttribute("title")
	public String getBoard() {
		return name;
	}
	
	@GetMapping("/join")
	public void join() {
	}
	
	@PostMapping("/join")
	public String join(Model model, MemberVO memberVO, MultipartFile profile) throws Exception {
		int result = memberService.join(memberVO, profile);
		
		model.addAttribute("msg", "가입 성공");
		model.addAttribute("url", "/");
		
		return "/common/result";
	}
}
