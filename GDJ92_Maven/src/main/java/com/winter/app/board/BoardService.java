package com.winter.app.board;

import java.util.List;

public interface BoardService {
	
	List<BoardVO> list() throws Exception;
	BoardVO select(BoardVO param) throws Exception;
}
