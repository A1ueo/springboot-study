package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardVO;
import com.winter.app.common.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String name;
	
	@ModelAttribute("title")
	public String getBoard() {
		return name;
	}
	
	@GetMapping("/list")
	public String list(Model model, Pager pager) throws Exception {
		List<BoardVO> list = qnaService.list(pager);
		
		model.addAttribute("list", list);
		model.addAttribute("title", "QnA");
		model.addAttribute("pager", pager);
		
		return "/board/list";
	}
	
	@GetMapping("/add")
	public String add() throws Exception {
		
		return "/board/form";
	}
	
	@PostMapping("/add")
	public String add(Model model, QnaVO qnaVO, MultipartFile[] attaches) throws Exception {
		int result = qnaService.insert(qnaVO, attaches);
		
		String url = "./list";
		String msg = "작성 실패";
		if (result > 0) {
			msg = "작성 성공";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "/common/result";
	}
	
	@GetMapping("/detail")
	public String detail(Model model, QnaVO qnaVO) throws Exception {
		BoardVO boardVO = qnaService.detail(qnaVO);
		
		if (boardVO == null) {
			String url = "./list";
			String msg = "삭제된 게시물입니다";
			
			model.addAttribute("url", url);
			model.addAttribute("msg", msg);
			
			return "/common/result";
		}
		
		model.addAttribute("board", boardVO);
		
		return "/board/detail";
	}
	
	@GetMapping("/reply")
	public String reply(Model model, QnaVO qnaVO) throws Exception {
		model.addAttribute("board", qnaVO);
		
		return "/board/form";
	}
	
	@PostMapping("/reply")
	public String reply(QnaVO qnaVO) throws Exception {
		int result = qnaService.reply(qnaVO);
		
//		return "redirect:./detail?boardNum=" + qnaVO.getBoardNum();
		return "redirect:./list";
	}
	
	@GetMapping("/update")
	public String update(Model model, BoardVO noticeVO) throws Exception {
		BoardVO boardVO = qnaService.detail(noticeVO);
		
		model.addAttribute("board", boardVO);
		
		return "/board/form";
	}
	
	@PostMapping("/update")
	public String update(Model model, QnaVO qnaVO, MultipartFile[] attaches) throws Exception {
		int result = qnaService.update(qnaVO, attaches);
		
		String msg = "수정 실패";
		
		if (result > 0) {
			msg = "수정 성공";
		}
		
		String url = "./detail?boardNum=" + qnaVO.getBoardNum();
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/common/result";
	}
	
	@PostMapping("/delete")
	public String delete(Model model, QnaVO qnaVO) throws Exception {
		int result = qnaService.delete(qnaVO);
		
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
	public int deleteOneFile(Model model, BoardFileVO boardFileVO) throws Exception {
		int result = qnaService.deleteOneFile(boardFileVO);
		
		return result;
	}
}












