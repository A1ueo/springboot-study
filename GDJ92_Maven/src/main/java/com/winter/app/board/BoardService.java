package com.winter.app.board;

import java.util.List;

public interface BoardService {

	int insert(BoardVO boardVO) throws Exception;
	BoardVO detail(BoardVO boardVO) throws Exception;
	List<BoardVO> list() throws Exception;
}
