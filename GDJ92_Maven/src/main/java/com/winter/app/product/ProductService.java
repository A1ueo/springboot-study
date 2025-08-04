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

	public int insert(ProductVO param) throws Exception {
		return productDAO.insert(param);
	}

	public List<ProductKindVO> selectKindList() throws Exception {
		return productDAO.selectKindList();
	}

	public int update(ProductVO productVO) throws Exception {
		return productDAO.update(productVO);
	}

	public int delete(ProductVO productVO) throws Exception {
		return productDAO.delete(productVO);
	}
}
