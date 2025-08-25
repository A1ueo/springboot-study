package com.winter.app.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	int join(MemberVO memberVO) throws Exception;
	int joinProfile(ProfileVO profileVO) throws Exception;
	int joinRole(Map<String, Object> map) throws Exception;
	MemberVO login(MemberVO memberVO) throws Exception;
	int cartAdd(CartVO cartVO) throws Exception;
	int deleteCart(Map<String, Object> map) throws Exception;
	int checkUsername(MemberVO memberVO) throws Exception;
	int update(MemberVO memberVO) throws Exception;
	int passwordUpdate(MemberVO memberVO) throws Exception;
}
