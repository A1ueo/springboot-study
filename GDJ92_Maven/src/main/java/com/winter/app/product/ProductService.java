package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	public List<ProductDTO> list() throws Exception {
		return productDAO.list();
	}
	
	public ProductDTO select(ProductDTO param) throws Exception {
		return productDAO.select(param);
	}
}
