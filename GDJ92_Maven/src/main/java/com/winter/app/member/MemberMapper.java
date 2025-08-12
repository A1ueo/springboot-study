package com.winter.app.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	int join(MemberVO memberVO) throws Exception;
	int joinProfile(ProfileVO profileVO) throws Exception;
}
