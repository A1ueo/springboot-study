package com.winter.app.boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	@Autowired
	private BoardService boardService;

	// /borad/list =>
	// /board/detail =>
	
//	public BoardController() {
//		this.boardService = new BoardService();
//	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
//		System.out.println("boardList");
		boardService.list();
		
		return "/board/list";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detail(BoardVO boardVO) {
//		String num = request.getParameter("num");
//		int n = Integer.parseInt(num);
		
//		BoardVO boardVO = new BoardVO();
//		boardVO.setNum(num);
//		boardVO.setName(kind);
		
//		System.out.println("boardDetail: " + boardVO);
		// return이 없어도 url을 기준으로 찾아감
	}
	
}
