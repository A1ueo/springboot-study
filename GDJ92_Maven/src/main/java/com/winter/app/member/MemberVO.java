package com.winter.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winter.app.member.validation.AddGroup;
import com.winter.app.member.validation.UpdateGroup;

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
public class MemberVO implements UserDetails {

	@NotBlank(groups = AddGroup.class)
	private String username;
	@Size(min = 6, max = 8)	// 길이
	@NotBlank(groups = {AddGroup.class})
	private String password;
	
	private String passwordCheck;
	
	@NotBlank(groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효하지 않은 이메일 형식입니다", 
			groups = {AddGroup.class, UpdateGroup.class})
	private String email;
	@Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "유효하지 않은 전화번호 형식입니다", 
			groups = {AddGroup.class, UpdateGroup.class})	// 정규 표현식
	private String phone;
	@Past(message = "유효하지 않은 날짜입니다", groups = {AddGroup.class, UpdateGroup.class})
	@NotNull
	private LocalDate birth;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credintialsNonExpired;
	private boolean enabled;
	
	private ProfileVO profileVO;
	private List<RoleVO> roleVOs;

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		for (RoleVO r : roleVOs) {
			list.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
		
		return list;
	}
}
