package com.winter.app.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserVO {

	private Long id;
	private String name;
	private String username;
	private String email;
}
