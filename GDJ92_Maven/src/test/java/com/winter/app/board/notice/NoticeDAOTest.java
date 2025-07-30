package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void insertTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title3");
		noticeVO.setBoardContent("content3");
		noticeVO.setBoardWriter("writer3");
		
		int result = noticeDAO.insert(noticeVO);
		
		// 단정문
		assertEquals(1, result);
	}
	
	@Test
	void updateTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("titleUPD");
		noticeVO.setBoardContent("contentUPD");
		noticeVO.setBoardNum(2);
		
		int result = noticeDAO.update(noticeVO);
		assertEquals(1, result);
	}
	
	@Test
	void deleteTest() throws Exception {
		int result = noticeDAO.delete(1);
		assertEquals(1, result);
	}

}
