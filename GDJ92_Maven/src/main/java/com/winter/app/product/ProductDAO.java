package com.winter.app.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDAO {
	List<ProductDTO> list() throws Exception;
	ProductDTO select(ProductDTO param) throws Exception;
}
