package com.winter.app.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.member.MemberVO;

@Service
public class AccountService {

	@Autowired
	AccountMapper accountDAO;
	
	public void signUp(MemberVO memberVO, Long[] numArr) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("username", memberVO.getUsername());
		map.put("numArr", numArr);
		
		accountDAO.signUp(map);
	}

}
