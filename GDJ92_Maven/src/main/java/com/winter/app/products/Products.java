package com.winter.app.products;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/*")
public class Products {

	@GetMapping("/list")
	public String list() {
		
		return "/products/list";
	}

	@GetMapping("/detail")
	public String detail() {
		
		return "/products/detail";
	}

	@GetMapping("/add")
	public String add() {
		
		return "/products/products_form";
	}

	@GetMapping("/update")
	public String update() {

		return "/products/products_form";
	}
}
