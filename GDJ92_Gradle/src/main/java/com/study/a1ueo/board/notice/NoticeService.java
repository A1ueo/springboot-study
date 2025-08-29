package com.study.a1ueo.board.notice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;

	@Transactional
	public NoticeVO detail(Long id) throws Exception {
		Optional<NoticeVO> result = noticeRepository.findById(id);
		
		return result.orElseThrow();
	}

	public Page<NoticeVO> list(Pageable pageable) {
		return noticeRepository.findAll(pageable);
	}
}
