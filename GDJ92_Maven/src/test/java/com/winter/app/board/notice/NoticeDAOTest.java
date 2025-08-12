package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	@Rollback(false)
	void insertTest() throws Exception {
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("titleDelete");
		noticeVO.setBoardContent("content");
		noticeVO.setBoardWriter("writer");
		
		int result = noticeDAO.insert(noticeVO);
		
		// 단정문
		assertEquals(1, result);
	}
	
	@Test
	void updateTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("titleUPD");
		noticeVO.setBoardContent("contentUPD");
		noticeVO.setBoardNum(2L);
		
		int result = noticeDAO.update(noticeVO);
		assertEquals(1, result);
	}
	
//	@Test
//	void deleteTest() throws Exception {
//		int result = noticeDAO.delete(5);
//		assertEquals(1, result);
//	}
	
	@Test
	void detailTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(2L);
		BoardVO boardVO = noticeDAO.detail(noticeVO);
		
		log.info("result: {}", boardVO);
		assertNotNull(boardVO);
	}
	
	@Test
	void listTest() throws Exception {
		List<BoardVO> list = null; //noticeDAO.list();
		
		assertNotEquals(list.size(), 0);
	}

}
