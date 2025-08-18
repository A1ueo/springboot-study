package com.winter.app.member;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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

	@NotBlank
	private String username;
	@Size(min = 6, max = 8)	// 길이
	private String password;
	@NotBlank
	private String name;
	@Email
	private String email;
	// @Pattern(regexp = "")	// 정규 표현식
	private String phone;
	@Past
	private LocalDate birth;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credintialsNonExpired;
	private boolean enabled;
	
	private ProfileVO profileVO;
	private List<RoleVO> roleVOs;
}
