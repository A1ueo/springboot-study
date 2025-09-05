package com.study.a1ueo.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MemberVO> result = memberRepository.findById(username);
		
		return result.get();
	}
	
//	public void updatePassword() {
//		MemberVO memberVO = new MemberVO();
//		memberVO.setUsername("user01");
//		memberVO.setPassword(passwordEncoder.encode("user01"));
//		memberRepository.save(memberVO);
//	}

}
