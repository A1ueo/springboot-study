package com.winter.app.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/signUp")
	@ResponseBody
	public int signUp(HttpSession session, Long[] numArr) throws Exception {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		return accountService.signUp(memberVO, numArr);
	}
}
