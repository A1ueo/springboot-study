package com.winter.app.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.member.MemberVO;

@Service
public class AccountService {

	@Autowired
	AccountMapper accountDAO;
	
	public int signUp(MemberVO memberVO, Long[] numArr) throws Exception {
		List<AccountVO> list = new ArrayList<>();
		
		String username = memberVO.getUsername();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddN");
		
		for (Long n : numArr) {
//			LocalDateTime now = LocalDateTime.now();
//			String num = now.format(formatter).substring(0, 14);
			
			AccountVO accountVO = new AccountVO();
			accountVO.setAccountNum(String.valueOf(System.currentTimeMillis()));
			accountVO.setUsername(username);
			accountVO.setProductNum(n);
//			accountVO.setAccountDate(now.toLocalDate());
//			accountVO.setAccountBalance(0L);
			
			list.add(accountVO);
			Thread.sleep(10);
		}
		
		return accountDAO.signUp(list);
	}

}
