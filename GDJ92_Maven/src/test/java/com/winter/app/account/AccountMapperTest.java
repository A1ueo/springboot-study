package com.winter.app.account;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AccountMapperTest {

	@Autowired
	private AccountMapper accountDAO;
	
	@Test
	@DisplayName("Account Add Test")
	void testSignUp() throws Exception {
		List<AccountVO> list = new ArrayList<>();
		AccountVO accountVO = new AccountVO();
		accountVO.setAccountNum("1");
		accountVO.setUsername("user1");
		accountVO.setProductNum(1L);
		list.add(accountVO);
		
		accountVO = new AccountVO();
		accountVO.setAccountNum("2");
		accountVO.setUsername("user1");
		accountVO.setProductNum(5L);
		list.add(accountVO);
		
		int result = accountDAO.signUp(list);
		
		assertNotEquals(0, result);
	}

}
