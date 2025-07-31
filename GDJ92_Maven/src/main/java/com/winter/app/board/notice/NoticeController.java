package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {

//	@Autowired
//	private NoticeDAO noticeDAO;
	@Autowired
	private NoticeService noticeService;
	
//	@GetMapping("/add")
//	public void insert() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContent("content");
//		noticeVO.setBoardWriter("writer");
//		
//		int result = noticeDAO.insert(noticeVO);
//	}
	
	@GetMapping("/list")
	public void list(Model model) throws Exception {
		List<BoardVO> list = noticeService.list();
		model.addAttribute("list", list);
	}
	
	@GetMapping("/detail")
	public void detail(Model model, Long boardNum) throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(boardNum);
		
		BoardVO boardVO = noticeService.select(noticeVO);
		
		if (boardVO != null) {
			model.addAttribute("notice", boardVO);
		}
	}
}
