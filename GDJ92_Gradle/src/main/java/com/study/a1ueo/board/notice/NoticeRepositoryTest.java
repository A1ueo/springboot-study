package com.study.a1ueo.board.notice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	void testSave() {
		for (int i=0; i<100; i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setBoardTitle("title" + i);
			noticeVO.setBoardContents("contents" + i);
			noticeVO.setBoardWriter("writer" + i);
			noticeRepository.save(noticeVO);
		}
	}

}
