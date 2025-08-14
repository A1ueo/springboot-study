package com.winter.app.product;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductTest {

	@Autowired
	ProductDAO productDAO;
	
	@Test
	void insert() throws Exception {
		for (int i = 0; i < 10; i++) {
			ProductVO productVO = new ProductVO();
			productVO.setProductName("title" + i);
			productVO.setProductContent("content" + i);
			productVO.setProductDate(LocalDate.now());
			productVO.setProductRate(1.11);
			productVO.setKindNum(i % 3 + 1L);
			
			productDAO.insert(productVO);
		}
	}

}
