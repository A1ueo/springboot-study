package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public void detail(Model model, NoticeVO noticeVO) throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		
		model.addAttribute("notice", boardVO);
	}
	
	@GetMapping("/add")
	public void add() {
		
	}
	
	@PostMapping("/add")
	public String add(NoticeVO noticeVO) throws Exception {
		int result = noticeService.insert(noticeVO);
		return "redirect:./list";
	}
	
	@GetMapping("/update")
	public String update(Model model, BoardVO noticeVO) throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		
		model.addAttribute("notice", boardVO);
		
		return "/notice/add";
	}
	
	@PostMapping("/update")
	public String update(Model model, NoticeVO noticeVO) throws Exception {
		int result = noticeService.update(noticeVO);
		
		String msg = "수정 실패";
		
		if (result > 0) {
			msg = "수정 성공";
		}
		
		String url = "./detail?boardNum=" + noticeVO.getBoardNum();
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/common/result";
	}
	
	@PostMapping("/delete")
	public String delete(Model model, NoticeVO noticeVO) throws Exception {
		int result = noticeService.delete(noticeVO);
		
		String msg = "삭제 실패";
		
		if (result > 0) {
			msg = "삭제 성공";
		}

		String url = "./list";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/common/result";
	}
}
