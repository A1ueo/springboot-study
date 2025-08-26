package com.winter.app.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.common.FileManager;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberMapper memberDAO;
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	@Value("${board.member}")
	private String board;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.login(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberVO;
	}
	
	// 검증 메서드
	public boolean hasMemeberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		// check: true => 검증 실패
		// check: false => 검증 통과
		
		// 1. Annotation 검증
		boolean check = bindingResult.hasErrors();
		
		// 2. 사용자 정의로 패스워드가 일치하는지 검증
		if (!Objects.equals(memberVO.getPassword(), memberVO.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck", "member.password.check");
		}
		
		// 3. ID 중복 검사
		int count = memberDAO.checkUsername(memberVO);
		if (count > 0) {
			check = true;
			bindingResult.rejectValue("username", "member.username.check");
		}
		
		return check;
	}
	
	int join(MemberVO memberVO, MultipartFile file) throws Exception {
		int result = memberDAO.join(memberVO);
		
		if (result != 1) return -1;
		
		ProfileVO profileVO = new ProfileVO();
		profileVO.setUsername(memberVO.getUsername());
		
		if (file == null || file.isEmpty()) {
			profileVO.setOriName("profile.png");
			profileVO.setSaveName("profile.png");
		} else {
			String saveName = fileManager.fileSave(upload + board, file);
			
			profileVO.setOriName(file.getOriginalFilename());
			profileVO.setSaveName(saveName);
		}
		
		result = memberDAO.joinProfile(profileVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
//		map.put("roleNum", 3);
		
		result = memberDAO.joinRole(map);
		
		return result;
	}
	
//	MemberVO login(MemberVO memberVO) throws Exception {
//		MemberVO result = memberDAO.login(memberVO);
//		
//		if (result != null) {
//			return result;
//		}
//		
//		return null;
//	}
	
	int cartAdd(CartVO cartVO) throws Exception {
		return memberDAO.cartAdd(cartVO);
	}
/*
	public int deleteCart(CartVO cartVO) throws Exception {
		return memberDAO.deleteCart(cartVO);
	}
*/
	public int deleteCart(MemberVO memberVO, Long[] numArr) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("numArr", numArr);
		
		return memberDAO.deleteCart(map);
	}

	public int update(MemberVO memberVO) throws Exception {
		return memberDAO.update(memberVO);
	}
	
}
