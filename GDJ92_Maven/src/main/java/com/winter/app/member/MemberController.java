package com.winter.app.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.product.ProductService;
import com.winter.app.product.ProductVO;

import jakarta.servlet.http.HttpSession;

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
	public void join() {
	}
	
	@PostMapping("/join")
	public String join(Model model, MemberVO memberVO, MultipartFile profile) throws Exception {
		int result = memberService.join(memberVO, profile);
		
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
}
