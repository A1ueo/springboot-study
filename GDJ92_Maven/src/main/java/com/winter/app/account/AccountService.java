package com.winter.app.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.member.MemberMapper;
import com.winter.app.member.MemberVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {

	@Autowired
	AccountMapper accountDAO;
	@Autowired
	MemberMapper memberDAO;
	
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
		int result = accountDAO.signUp(list);
		
		if (result > 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("username", username);
			map.put("numArr", numArr);
			memberDAO.deleteCart(map);
		}
		
		return result;
	}

}
