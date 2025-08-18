package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardVO;
import com.winter.app.common.Pager;
import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/notice/*")
@Slf4j
public class NoticeController {

//	@Autowired
//	private NoticeDAO noticeDAO;
	@Autowired
	private NoticeService noticeService;
	
	@Value("${board.notice}")
	private String name;
	
//	@GetMapping("/add")
//	public void insert() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContent("content");
//		noticeVO.setBoardWriter("writer");
//		
//		int result = noticeDAO.insert(noticeVO);
//	}
	
	@ModelAttribute("title")
	public String getBoard() {
		return name;
	}
	
	@GetMapping("/list")
	public String list(Model model, /* @ModelAttribute */ Pager pager) throws Exception {
		List<BoardVO> list = noticeService.list(pager);
		
		model.addAttribute("list", list);
//		model.addAttribute("pager", pager);
		
		return "/board/list";
	}
	
	@GetMapping("/detail")
	public String detail(Model model, NoticeVO noticeVO) throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		
		model.addAttribute("board", boardVO);
		
		return "/board/detail";
	}
	
	@GetMapping("/add")
	public String add(/* Model model, */ @ModelAttribute("boardVO") BoardVO noticeVO) {
//		model.addAttribute("boardVO", new NoticeVO());
		return "/board/form";
	}
	
	@PostMapping("/add")
	public String add(HttpSession session, @Valid BoardVO noticeVO, BindingResult bindingResult, MultipartFile[] attaches) throws Exception {
		if (bindingResult.hasErrors()) {
			return "/board/form";
		}
		
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		noticeVO.setBoardWriter(memberVO.getUsername());
		
		int result = noticeService.insert(noticeVO, attaches);
		
		return "redirect:./list";
	}
	
	@GetMapping("/update")
	public String update(Model model, BoardVO noticeVO) throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		
		model.addAttribute("board", boardVO);
		
		return "/board/form";
	}
	
	@PostMapping("/update")
	public String update(Model model, NoticeVO noticeVO, MultipartFile[] attaches) throws Exception {
		int result = noticeService.update(noticeVO, attaches);
		
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
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public int deleteOneFile(BoardFileVO boardFileVO) throws Exception {
		int result = noticeService.deleteOneFile(boardFileVO);
		
		return result;
	}
	
	@GetMapping("/fileDown")
	public String fileDown(Model model, BoardFileVO boardFileVO) throws Exception {
		boardFileVO = noticeService.fileDetail(boardFileVO);
		
		model.addAttribute("vo", boardFileVO);
		
		return "fileDownView";
	}
	
	@PostMapping("/boardFile")
	@ResponseBody
	public String boardFile(MultipartFile bf) throws Exception {
		return noticeService.boardFile(bf);
	}
	
	@PostMapping("/boardFileDelete")
	@ResponseBody
	public boolean boardFileDelete(String fileName) throws Exception {
		return noticeService.boardFileDelete(fileName);
	}
}






