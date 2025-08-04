package com.winter.app.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductKindVO {

	private Long kindNum;
	private String kindName;
	
	// 1:N
//	private List<ProductVO> list;
}
