package com.winter.app.product;

import java.time.LocalDateTime;

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
public class ProductDTO {

	private Long productNum;
	private String productName;
	private String productContents;
	private LocalDateTime productDate;
	private Double productRate;
	private Long kindNum;
}
