package com.winter.app.member;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
	@NotBlank
	private String password;
	
	private String passwordCheck;
	
	@NotBlank
	private String name;
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효하지 않은 이메일 형식입니다")
	private String email;
	@Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "유효하지 않은 전화번호 형식입니다")	// 정규 표현식
	private String phone;
	@Past(message = "유효하지 않은 날짜입니다")
	@NotNull(message = "공백일 수 없습니다")
	private LocalDate birth;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credintialsNonExpired;
	private boolean enabled;
	
	private ProfileVO profileVO;
	private List<RoleVO> roleVOs;
}
