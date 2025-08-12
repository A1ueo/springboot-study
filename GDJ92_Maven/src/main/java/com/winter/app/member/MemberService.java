package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.common.FileManager;

@Service
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
		
		return result;
	}
}
