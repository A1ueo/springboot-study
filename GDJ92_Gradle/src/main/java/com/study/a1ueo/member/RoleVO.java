package com.study.a1ueo.member;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "role")
public class RoleVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleNum;
	private String roleName;
	
	@OneToMany(mappedBy = "roleVO")
	List<MemberRoleVO> memberRoleVOs;
}
