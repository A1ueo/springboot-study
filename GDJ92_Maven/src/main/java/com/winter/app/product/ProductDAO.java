package com.winter.app.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDAO {
	List<ProductVO> list() throws Exception;
	ProductVO select(ProductVO param) throws Exception;
	ProductVO detail(ProductVO param) throws Exception;
}
