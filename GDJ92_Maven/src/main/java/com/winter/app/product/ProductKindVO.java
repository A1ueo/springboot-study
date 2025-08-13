package com.winter.app.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductKindVO {

	private Long kindNum;
	private String kindName;
	
	// 1:N
//	private List<ProductVO> list;
}
