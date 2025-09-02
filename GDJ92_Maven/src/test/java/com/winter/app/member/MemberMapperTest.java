package com.winter.app.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberMapperTest {

	@Autowired
	MemberMapper memberDAO;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	void testPasswordUpdate() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("user01");
		
		memberVO.setPassword(passwordEncoder.encode("user01"));
		
		int result = memberDAO.passwordUpdate(memberVO);
		
		assertEquals(result, 1);
	}

}
