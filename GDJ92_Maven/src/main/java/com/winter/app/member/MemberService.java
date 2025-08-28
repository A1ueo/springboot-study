package com.winter.app.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.common.FileManager;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService {

	@Autowired
	private MemberMapper memberDAO;
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${app.upload}")
	private String upload;
	@Value("${board.member}")
	private String board;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		log.info("{}", userRequest.getAccessToken());
//		log.info("{}", userRequest.getAdditionalParameters());
//		log.info("{}", userRequest.getClientRegistration());
		
		String sns = userRequest.getClientRegistration().getRegistrationId();
		
		OAuth2User user = null;
		if (sns.toUpperCase().equals("KAKAO")) {
			user = this.useKakao(userRequest);
		}
		
		return user;
	}
	
	private OAuth2User useKakao(OAuth2UserRequest userRequest) {
		OAuth2User user = super.loadUser(userRequest);
		
//		log.info("{}", user.getName());
//		log.info("{}", user.getAttributes());
//		log.info("{}", user.getAuthorities());
		
		Map<String, Object> map = user.getAttributes();
		LinkedHashMap<String, Object> m = (LinkedHashMap<String, Object>) map.get("properties");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(m.get("nickname").toString());
		memberVO.setName(user.getName());
		
		ProfileVO profileVO = new ProfileVO();
		profileVO.setSaveName(m.get("profile_image").toString());
		memberVO.setProfileVO(profileVO);
		
		List<RoleVO> roleVOs = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");
		roleVOs.add(roleVO);
		memberVO.setRoleVOs(roleVOs);
		
		memberVO.setAttributes(map);
		
		memberVO.setSns("kakao");
		memberVO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		
		return memberVO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		
		try {
			memberVO = memberDAO.login(memberVO);
			
			return memberVO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		
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
//		passwordEncoder.matches(memberVO.getPassword(), passwordEncoder.encode("user1"));
		
		return memberDAO.update(memberVO);
	}

	public int delete(MemberVO memberVO) {
		WebClient webClient = WebClient.create();
		
		Mono<Map> result = webClient.post()
				.uri("https://kapi.kakao.com/v1/user/unlink")
				.header("Authorization", "Bearer " + memberVO.getAccessToken())
				.retrieve()
				.bodyToMono(Map.class)
				;
		
		log.info("{}", result.block());
		// if (Objects.equals(result.block().get("id"), memberVO.get)) // 아이디 비교
		
		return 1;
	}
}
