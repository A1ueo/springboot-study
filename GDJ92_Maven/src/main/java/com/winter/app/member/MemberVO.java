package com.winter.app.member;

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
public class MemberVO {

	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String birth;
	
	ProfileVO profileVO;
}
