package com.study.a1ueo.board.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public NoticeVO detail(Long id) throws Exception {
		Optional<NoticeVO> result = noticeRepository.findById(id);
		
		return result.orElseThrow();
	}

	public List<NoticeVO> list() {
		return noticeRepository.findAll();
	}
}
