package com.winter.app.transfer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.member.MemberVO;

@SpringBootTest
class TransferTest {

	@Autowired
	private Transfers transfers;
	@Autowired
	private Pay pay;
	
	@Test
	void test() {
		
		transfers.takeBus("111");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("user");
		memberVO = transfers.takeSubway(memberVO);
		
	}

}
