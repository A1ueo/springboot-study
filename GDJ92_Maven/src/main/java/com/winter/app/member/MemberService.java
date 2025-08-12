package com.winter.app.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.common.FileManager;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberMapper memberDAO;
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	@Value("${board.profile}")
	private String board;
	
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
	
	MemberVO login(MemberVO memberVO) throws Exception {
		MemberVO result = memberDAO.login(memberVO);
		
		if (result != null) {
			return result;
		}
		
		return null;
	}
}
