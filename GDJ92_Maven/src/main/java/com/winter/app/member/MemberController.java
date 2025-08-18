package com.winter.app.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.product.ProductService;
import com.winter.app.product.ProductVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
	
	@GetMapping("/join")
	public void join(MemberVO memberVO) throws Exception {
	}
	
	@PostMapping("/join")
	public String join(Model model, @Valid MemberVO memberVO, BindingResult bindingResult, 
			MultipartFile profile) throws Exception {
		memberService.hasMemeberError(memberVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/member/join";
		}
		
		// int result = memberService.join(memberVO, profile);
		
		model.addAttribute("msg", "가입 성공");
		model.addAttribute("url", "/");
		
		return "/common/result";
	}
	
	@GetMapping("/login")
	public void login() throws Exception {
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, MemberVO memberVO) throws Exception {
		memberVO = memberService.login(memberVO);
		
		if (memberVO != null) {
			session.setAttribute("member", memberVO);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("member");
		session.invalidate();
		
		return "redirect:/";
	}
	
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
}
