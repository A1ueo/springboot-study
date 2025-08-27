package com.winter.app.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostVO {

	private Long id;
	private String title;
	private String body;
	private Long userId;
}
