package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public void list(Model model) throws Exception {
		List<ProductDTO> list = productService.list();
		
		model.addAttribute("list", list);
	}

	@GetMapping("/detail")
	public void detail(Model model, ProductDTO param) throws Exception {
		ProductDTO result = productService.select(param);
		
		model.addAttribute("product", result);
	}

	@GetMapping("/add")
	public String add() throws Exception {
		
		return "/product/product_form";
	}

	@GetMapping("/update")
	public String update() throws Exception {

		return "/product/product_form";
	}
}
