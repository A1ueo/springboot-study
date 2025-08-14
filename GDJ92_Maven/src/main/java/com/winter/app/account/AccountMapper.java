package com.winter.app.account;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

	void signUp(Map<String, Object> map) throws Exception;
}
