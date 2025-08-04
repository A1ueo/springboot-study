package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public void list(Model model) throws Exception {
		List<ProductVO> list = productService.list();
		
		model.addAttribute("list", list);
	}

	@GetMapping("/detail")
	public void detail(Model model, ProductVO param) throws Exception {
		ProductVO result = productService.detail(param);
		
		model.addAttribute("product", result);
	}

	@GetMapping("/add")
	public String add(Model model) throws Exception {
		List<ProductKindVO> list = productService.selectKindList();
		
		model.addAttribute("list", list);
		
		return "/product/product_form";
	}

	@PostMapping("/add")
	public String add(ProductVO param) throws Exception {
		int result = productService.insert(param);
		
		return "redirect:./list";
	}

	@GetMapping("/update")
	public String update(Model model, ProductVO param) throws Exception {
		List<ProductKindVO> list = productService.selectKindList();
		ProductVO result =productService.detail(param);

		model.addAttribute("list", list);
		model.addAttribute("product", result);

		return "/product/product_form";
	}
	
	@PostMapping("/update")
	public String update(ProductVO productVO, Model model) throws Exception {
		int result = productService.update(productVO);
		
		String url = "./detail?productNum=" + productVO.getProductNum();
		
		String msg = "수정 실패";
		if (result > 0) {
			msg = "수정 성공";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "/common/result";
	}
	
	@PostMapping("/delete")
	public String delete(Model model, ProductVO productVO) throws Exception {
		int result = productService.delete(productVO);
		
		String url = "./list";
		
		String msg = "삭제 실패";
		if (result > 0) {
			msg = "삭제 성공";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "/common/result";
	}
}
