package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	public List<ProductVO> list() throws Exception {
		return productDAO.list();
	}
	
	public ProductVO select(ProductVO param) throws Exception {
		return productDAO.select(param);
	}
	
	public ProductVO detail(ProductVO param) throws Exception {
		return productDAO.detail(param);
	}
}
