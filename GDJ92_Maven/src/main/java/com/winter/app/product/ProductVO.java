package com.winter.app.product;

import java.time.LocalDate;

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
public class ProductVO {

	private Long productNum;
	private String productName;
	private String productContents;
	private LocalDate productDate;
	private Double productRate;
	private Long kindNum;
	
	// 1:1
	// 단방향
	private ProductKindVO productKindVO;
}
