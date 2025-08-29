package com.study.a1ueo.member;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member_role")
@IdClass(MemberRolePK.class)
public class MemberRoleVO {

	@EmbeddedId
	private MemberRolePK memberRolePK;
	
//	@Id
//	private String username;
//	@Id
//	private Long roleNum;
	
	@JoinColumn(name = "username")
	@ManyToOne
	@JsonIgnore
	MemberVO memberVO;
	@JoinColumn(name = "roleNum")
	@ManyToOne
	@JsonIgnore
	RoleVO roleVO;
}
