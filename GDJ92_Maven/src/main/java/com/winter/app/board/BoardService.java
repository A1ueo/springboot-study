package com.winter.app.board;

import java.util.List;

import com.winter.app.common.Pager;

public interface BoardService {

	int insert(BoardVO boardVO) throws Exception;
	BoardVO detail(BoardVO boardVO) throws Exception;
	List<BoardVO> list(Pager pager) throws Exception;
	int update(BoardVO boardVO) throws Exception;
	int delete(BoardVO boardVO) throws Exception;
}
