package com.winter.app.member;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.member.validation.AddGroup;
import com.winter.app.member.validation.UpdateGroup;
import com.winter.app.product.ProductService;
import com.winter.app.product.ProductVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ProductService productService;

	@Value("${board.member}")
	private String name;
	
	@ModelAttribute("title")
	public String getBoard() {
		return name;
	}
	
	@GetMapping("/update")
	public String update(Authentication authentication, Model model) throws Exception {
		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
		model.addAttribute("memberVO", memberVO);
		return "/member/memberUpdate";
	}
	
	@PostMapping("/update")
	public String update(@Validated(UpdateGroup.class) MemberVO memberVO, BindingResult bindingResult, 
			MultipartFile profile, Authentication authentication, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			return "/member/memberUpdate";
		}
		
		MemberVO logined = (MemberVO) authentication.getPrincipal();
		
		System.out.println(logined);
		
		memberVO.setUsername(logined.getUsername());
		int result = memberService.update(memberVO);
		
		String msg = "수정 실패";
		if (result > 0) {
			msg = "수정 성공";
//			memberVO.setPassword(logined.getPassword());
//			logined = memberService.login(memberVO);
//			session.setAttribute("member", logined);
		}
		String url = "./detail";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/common/result";
	}
	
	@GetMapping("/join")
	public String join(MemberVO memberVO, Authentication authentication) throws Exception {
		if (authentication != null) {
			return "/index";
		}
		return "/member/join";
	}
	
	@PostMapping("/join")
	public String join(Model model, @Validated({AddGroup.class, UpdateGroup.class}) MemberVO memberVO, BindingResult bindingResult, 
			MultipartFile profile) throws Exception {
		memberService.hasMemeberError(memberVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/member/join";
		}
		
		int result = memberService.join(memberVO, profile);
		
		model.addAttribute("msg", "가입 성공");
		model.addAttribute("url", "/");
		
		return "/common/result";
	}
	
	@GetMapping("/login")
	public String login(Principal principal) throws Exception {
		if (principal != null) {
			return "/index";
		}
		return "/member/login";
	}
	
//	@PostMapping("/login")
//	public String login(HttpSession session, MemberVO memberVO) throws Exception {
//		memberVO = memberService.login(memberVO);
//		
//		if (memberVO != null) {
//			session.setAttribute("member", memberVO);
//		}
//		
//		return "redirect:/";
//	}
//	
//	@GetMapping("/logout")
//	public String logout(HttpSession session) throws Exception {
//		session.removeAttribute("member");
//		session.invalidate();
//		
//		return "redirect:/";
//	}
	
	@GetMapping("/detail")
	public void detail() throws Exception {
	}
	
	@ResponseBody
	@PostMapping("/cartAdd")
	public int cartInsert(HttpSession session, CartVO cartVO) throws Exception {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		cartVO.setUsername(memberVO.getUsername());
		
		return memberService.cartAdd(cartVO);
	}
	
	@GetMapping("/cartList")
	public void cartInsert(HttpSession session, Model model) throws Exception {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		List<ProductVO> list = productService.cartList(memberVO);
		model.addAttribute("list", list);
	}
/*
	@ResponseBody
	@PostMapping("/deleteCart")
	public int deleteCart(HttpSession session, CartVO cartVO) throws Exception {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		cartVO.setUsername(memberVO.getUsername());
		
		return memberService.deleteCart(cartVO);
	}
*/
	@ResponseBody
	@PostMapping("/deleteCart")
	public int deleteCart(HttpSession session, Long[] numArr) throws Exception {
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		return memberService.deleteCart(memberVO, numArr);
	}
	
	@GetMapping("/delete")
	public String delete(@AuthenticationPrincipal MemberVO memberVO) throws Exception {
		int result = 0;
		
		if (memberVO.getSns() == null) {
			// service에서 삭제
		} else if (memberVO.getSns().equalsIgnoreCase("KAKAO")) {
			// 연결 해제
			result = memberService.delete(memberVO);
		}
		
		if (result > 0) return "redirect:/member/logout";
		
		return "";
	}
}
