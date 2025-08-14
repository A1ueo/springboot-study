package com.winter.app.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/singUp")
	public void signUp(HttpSession session, Long[] numArr) {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		accountService.signUp(memberVO, numArr);
	}
}
